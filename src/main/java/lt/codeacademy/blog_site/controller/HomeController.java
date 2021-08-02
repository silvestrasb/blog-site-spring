package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.User;
import lt.codeacademy.blog_site.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {


    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "redirect:/blogs";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/register")
    public String register(User user, BindingResult bindingResult, Model model, HttpServletRequest request) throws ServletException, RoleNotFoundException {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.createUser(user) == null) {
            model.addAttribute("user", new User());
            return "register";
        }

        request.login(user.getUsername(), user.getPassword());
        return "redirect:/";

    }
}
