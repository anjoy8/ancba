package club.neters.user.infra.shiro;


import org.apache.shiro.authc.UsernamePasswordToken;

public class AuthToken extends UsernamePasswordToken {

    private final String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
