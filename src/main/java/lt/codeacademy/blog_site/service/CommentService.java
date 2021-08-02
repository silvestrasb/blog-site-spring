package lt.codeacademy.blog_site.service;

import lt.codeacademy.blog_site.entity.Comment;
import lt.codeacademy.blog_site.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllByBlogId(Long blogId) {
        return commentRepository.getAllByBlogId(blogId);
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

//    TEMP:
    public Comment getCommentById(Long id){
        return commentRepository.getById(id);
    }
    public void deleteById(Long blogId){
        commentRepository.deleteById(blogId);
    }
}
