package club.neters.gateway.config.limit;

import club.neters.common.constant.CommonConstant;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

@Configuration
@Order(-200)
public class LimitFilter implements WebFilter {

    @Resource
    private RedisRateLimiter redisRateLimiter;

    @Resource
    private KeyResolver keyResolver;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String pattern : CommonConstant.GW_SECURITY_WHITELIST) {
            if (antPathMatcher.match(pattern, exchange.getRequest().getURI().getPath())) {
                return chain.filter(exchange);
            }
        }

        return keyResolver.resolve(exchange).flatMap(key ->
                redisRateLimiter.isAllowed(RouteDefinitionRouteLocator.DEFAULT_FILTERS, key)
                        .flatMap(response -> {
                            for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                                exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                            }

                            if (response.isAllowed()) {
                                return chain.filter(exchange);
                            }

                            setResponseStatus(exchange, HttpStatus.TOO_MANY_REQUESTS);
                            return exchange.getResponse().setComplete();
                        }));
    }
}
