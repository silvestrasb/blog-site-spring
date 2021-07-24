package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.Blog;
import lt.codeacademy.blog_site.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String getBlogs(Model model) {


        List<Blog> sortedList = blogService
                .getAll()
                .stream()
                .sorted(Comparator.comparingLong(Blog::getId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("blogList", sortedList);
        return "blog/index";
    }

    @GetMapping("/create")
    public String createBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog(Blog blog) {
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

}