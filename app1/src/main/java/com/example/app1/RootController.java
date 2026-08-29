package com.example.app1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Unscoped root endpoint used as the ALB/health-check target when this service
 * runs behind a load balancer (App1Controller is scoped under /app1, so it
 * doesn't answer at "/").
 */
@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "OK";
    }
}
