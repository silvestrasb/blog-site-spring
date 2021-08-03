package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.User;
import lt.codeacademy.blog_site.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

        if (bindingResult.hasErrors() || userService.createUser(user) == null) {
            model.addAttribute("user", new User());
            return "register";
        }

        request.login(user.getUsername(), user.getPassword());
        return "redirect:/";

    }
}
