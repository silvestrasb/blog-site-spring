package lt.codeacademy.blog_site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String test(){
        return "blog/index";
    }
}
