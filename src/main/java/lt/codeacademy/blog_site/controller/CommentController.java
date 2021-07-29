package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.Comment;
import lt.codeacademy.blog_site.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs/{blogId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long blogId, @PathVariable Long commentId) {
        commentService.deleteById(commentId);
        return "redirect:/blogs/" + blogId + "/view";
    }

    @GetMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long blogId, @PathVariable("commentId") Comment comment, Model model) {
        comment.setBlogId(blogId);
        model.addAttribute("comment", comment);
        return "comment/edit";
    }

    @PostMapping("/create")
    public String addComment(@PathVariable Long blogId, Comment comment) {
        comment.setBlogId(blogId);
        commentService.save(comment);
        return "redirect:/blogs/" + blogId + "/view";
    }

    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long blogId, @PathVariable Long commentId, Comment comment) {
        comment.setId(commentId);
        comment.setBlogId(blogId);
        commentService.save(comment);
        return "redirect:/blogs/" + comment.getBlogId() + "/view";
    }

}
