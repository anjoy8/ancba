package club.neters.core.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //  HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        Object rawPassword = authentication.getCredentials();
        // 单独对密码器做处理，注意大小写
        String md5Pwd = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
        if (!md5Pwd.toUpperCase().equals(userDetails.getPassword())) {
            throw new AuthenticationServiceException("密码错误");
        }

        // String input_code = req.getParameter("code");
        // String verify = (String) req.getSession().getAttribute("verifyCode");
        // if (input_code == null || !input_code.equals(verify)) {
        //     throw new AuthenticationServiceException("验证码错误");
        // }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
