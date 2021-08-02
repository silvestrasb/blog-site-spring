package lt.codeacademy.blog_site.controller;

import lt.codeacademy.blog_site.entity.Comment;
import lt.codeacademy.blog_site.entity.User;
import lt.codeacademy.blog_site.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @PreAuthorize("hasRole('USER') && authentication.principal.id == #comment.user.id || hasRole('ADMIN')")
    @GetMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long blogId, @PathVariable("commentId") Comment comment, Authentication authentication) {
        commentService.deleteById(comment.getId());
        return "redirect:/blogs/" + blogId + "/view";
    }

    @PreAuthorize("hasRole('USER') && authentication.principal.id == #comment.user.id || hasRole('ADMIN')")
    @GetMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long blogId, @PathVariable("commentId") Comment comment, Model model) {
        comment.setBlogId(blogId);
        model.addAttribute("comment", comment);
        return "comment/edit";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public String addComment(@PathVariable Long blogId, Comment comment, @AuthenticationPrincipal User user) {
        comment.setUser(user);
        comment.setBlogId(blogId);
        commentService.save(comment);
        return "redirect:/blogs/" + blogId + "/view";
    }

    @PreAuthorize("hasRole('USER') && authentication.principal.id == #comment.user.id || hasRole('ADMIN')")
    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long blogId,
                              @PathVariable("commentId") Comment comment, Comment postComment,
                              @AuthenticationPrincipal User user) {
        postComment.setUser(user);
        postComment.setId(comment.getId());
        postComment.setBlogId(blogId);
        commentService.save(postComment);
        return "redirect:/blogs/" + postComment.getBlogId() + "/view";
    }

}
