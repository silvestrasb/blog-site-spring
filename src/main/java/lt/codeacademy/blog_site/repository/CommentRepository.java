package lt.codeacademy.blog_site.repository;

import lt.codeacademy.blog_site.entity.Blog;
import lt.codeacademy.blog_site.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByBlogId(Long blogId);
}
