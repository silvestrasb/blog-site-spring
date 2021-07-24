package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @GetMapping
    public String getBlogs(){
        return "blog/index";
    }

    @GetMapping("/create")
    public String createBlog(Model model){
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog(){
        return "blog/view";
    }

}