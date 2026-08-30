package com.example.app1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

// No class-level @RequestMapping("/app1") here -- DeployMate's ALB deployment already mounts this
// whole app under /app1 via SERVER_SERVLET_CONTEXT_PATH (see application.yml/DeployMate's injected
// env var). Adding the same prefix again here double-stacks it (real requests would need
// /app1/app1/standalone to reach this controller), which is exactly the bug this fixes.
@RestController
public class App1Controller {

    private final RestClient app2RestClient;

    public App1Controller(RestClient app2RestClient) {
        this.app2RestClient = app2RestClient;
    }

    /**
     * Endpoint 1: calls an endpoint in App2 (via service discovery) and
     * returns a static string that embeds App2's response.
     */
    @GetMapping("/aggregate")
    public String aggregate() {
        String app2Response = app2RestClient.get()
                .uri("http://APP2/app2/data")
                .retrieve()
                .body(String.class);
        return "App1 /aggregate -> called App2, got: [" + app2Response + "]";
    }

    /**
     * Endpoint 2: just returns a static string.
     */
    @GetMapping("/standalone")
    public String standalone() {
        return "App1 /standalone -> static response, no downstream call";
    }
}
