package club.neters.blog.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController
@ApiIgnore
public class CSRFController {
    @GetMapping(value = "/csrf")
    public ResponseEntity<CsrfToken> getToken(HttpServletRequest request) {
        return ResponseEntity.ok().body(new HttpSessionCsrfTokenRepository().generateToken(request));
    }
}