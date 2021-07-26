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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Blog> sortedList = blogService
                .getAll()
                .stream()
                .map(blog -> {
                            blog.setContent(blog.getContent().substring(0, Math.min(blog.getContent().length(), 500)));
                            return blog;
                        }
                )
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

    @GetMapping("/{id}/view")
    public String getBlog(@PathVariable("id") Blog blog, Model model){
        model.addAttribute("blog", blog);
        model.addAttribute("comments", commentService.getAllByBlogId(blog.getId()));
        model.addAttribute("commentBox", new Comment());
        return "blog/view";
    }

    @GetMapping("/{id}/edit")
    public String editBlog(@PathVariable("id") Blog blog, Model model){
        model.addAttribute("blog", blog);
        return "blog/create";
    }


    @GetMapping("/{id}/delete")
    public String deleteCar(@PathVariable("id") Blog blog) {
        blogService.deleteBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/create")
    public String createBlog(Blog blog) {
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/add-comment")
    public String addComment(@PathVariable("id") Blog blog, Comment comment) {
        comment.setId(null);
        comment.setBlogId(blog.getId());
        commentService.save(comment);
        return "redirect:/blogs/" + blog.getId() + "/view";

    }
}