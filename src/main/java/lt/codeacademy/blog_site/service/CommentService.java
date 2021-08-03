package lt.codeacademy.blog_site.service;

import lt.codeacademy.blog_site.entity.Comment;
import lt.codeacademy.blog_site.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public void deleteById(Long blogId){
        commentRepository.deleteById(blogId);
    }
}
