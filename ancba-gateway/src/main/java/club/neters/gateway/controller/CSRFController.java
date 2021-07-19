package club.neters.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CSRFController {
//    @Value("${demo.user.name}")
//    private String userName;

    @GetMapping(value = "/v2/test")
    public String test() {
        return "ResponseEntity.ok()";
    }
}