package lt.codeacademy.blog_site.repository;

import lt.codeacademy.blog_site.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> getAllByOrderByIdDesc();
}
