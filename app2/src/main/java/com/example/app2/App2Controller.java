package com.example.app2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app2")
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
