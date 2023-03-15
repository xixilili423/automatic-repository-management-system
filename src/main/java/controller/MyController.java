package controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MyController {
    @GetMapping("/error")
    public String index() {
        return "index";
    }
}
