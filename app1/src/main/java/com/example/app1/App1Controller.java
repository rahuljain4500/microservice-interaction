package com.example.app1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/app1")
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
