package com.example.app2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// No class-level @RequestMapping("/app2") here -- DeployMate's ALB deployment already mounts this
// whole app under /app2 via SERVER_SERVLET_CONTEXT_PATH (see application.yml/DeployMate's injected
// env var). Adding the same prefix again here double-stacks it (real requests would need
// /app2/app2/data to reach this controller), which is exactly the bug this fixes.
@RestController
public class App2Controller {

    /**
     * The only App2 endpoint. Reachable either from App1's /aggregate call
     * or directly by a user through the API gateway (GET /app2/data).
     */
    @GetMapping("/data")
    public String data() {
        return "Hello from App2 /data (static response)";
    }
}
