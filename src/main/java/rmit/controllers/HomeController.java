package rmit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "This is Student Home Page";
    }

    @GetMapping("/teacher")
    public String teacher() {
        return "This is Teacher Home Page";
    }
}
