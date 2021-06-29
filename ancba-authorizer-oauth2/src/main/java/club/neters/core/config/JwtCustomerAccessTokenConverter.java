package club.neters.core.config;


import club.neters.domain.dto.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class JwtCustomerAccessTokenConverter extends DefaultAccessTokenConverter {

    public JwtCustomerAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
            //这里添加你的参数
            response.put("role", ((User) authentication.getPrincipal()).getUserRole());
            response.put("username", ((User) authentication.getPrincipal()).getUsername());
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }

            return response;
        }
    }
}