package club.neters.app.service;

import club.neters.domain.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUserName("laozhang");
        user.setUserPassword("123456");

        if (username.equals(user.getUsername())) {
            return user;
        }

        throw new UsernameNotFoundException("用户名不存在");
    }
}