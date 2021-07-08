package club.neters.gateway.config.security;

import club.neters.common.constant.CommonConstant;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    /**
     * 使用Redis处理api-role的关系
     * Todo 待扩展
     */
//    private RedisTemplate redisTemplate;
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();

        // 1、OPTIONS全部放过
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 取出路径+token
        PathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getURI().getPath();
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 2、token为空全部拒绝
        if (!(StringUtils.isNotBlank(token) && token.startsWith(CommonConstant.AUTHORIZATION_PREFIX))) {
            return Mono.just(new AuthorizationDecision(false));
        }


        // 3、从Redis中获取role-api关系集合
//        Map<String, Object> roleApiMap = redisTemplate.opsForHash().entries(CommonConstant.REDIS_SECURITY_ROLE_API_KEY);
        Map<String, Object> roleApiMap = new HashMap<String, Object>();
        // 测试数据，演示效果
        ArrayList<String> rolesOfUserTestPath = new ArrayList<>();
        rolesOfUserTestPath.add("AdminTest");
        roleApiMap.put("/user/test", rolesOfUserTestPath);

        // 4、不在roleApiMap中的，全部放过，仅做认证即可
        boolean doNotMoreAuthorizationCheck = true;
        Set<String> rolesOfCurrentPath = CollectionUtil.newHashSet();
        for (Map.Entry<String, Object> permRoles : roleApiMap.entrySet()) {
            String perm = permRoles.getKey();
            if (pathMatcher.match(perm, path)) {
                rolesOfCurrentPath.addAll(Convert.toList(String.class, permRoles.getValue()));
                doNotMoreAuthorizationCheck = false;
            }
        }
        if (doNotMoreAuthorizationCheck) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 5、判断用户JWT中携带的角色是否有能通过权限拦截的角色
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    String role = authority.substring("SCOPE_".length());
                    return !CollectionUtils.isEmpty(rolesOfCurrentPath) && rolesOfCurrentPath.contains(role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }
}