package com.example.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Root endpoint used as the ALB/health-check target. Doesn't collide with the
 * gateway's /app1/** and /app2/** routes, which are handled separately.
 */
@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "OK";
    }
}
