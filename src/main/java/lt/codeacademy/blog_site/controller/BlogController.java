package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.Blog;
import lt.codeacademy.blog_site.entity.Comment;
import lt.codeacademy.blog_site.service.BlogService;
import lt.codeacademy.blog_site.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;
    private final CommentService commentService;

    public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }


    @GetMapping
    public String getBlogs(Model model) {
        model.addAttribute("blogList", blogService.getAllFirstNCharacters(100));
        return "blog/index";
    }

    @GetMapping("/create")
    public String createBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @GetMapping("/{id}/view")
    public String getBlog(@PathVariable("id") Blog blog, Model model) {
        model.addAttribute("blog", blog);
        model.addAttribute("commentBox", new Comment());
        return "blog/view";
    }

    @GetMapping("/{id}/edit")
    public String editBlog(@PathVariable("id") Blog blog, Model model){
        model.addAttribute("blog", blog);
        return "blog/edit";
    }


    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable("id") Blog blog) {
        blogService.deleteBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/create")
    public String createBlog(Blog blog) {
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/edit")
    public String editBlog(@PathVariable("id") Blog dbBlog, Blog blog) {
        blog.setComments(dbBlog.getComments());
        blogService.saveBlog(blog);
        return "redirect:/blogs/" + blog.getId() + "/view";
    }


}