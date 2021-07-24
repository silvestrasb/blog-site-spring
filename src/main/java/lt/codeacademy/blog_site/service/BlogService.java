package lt.codeacademy.blog_site.service;

import lt.codeacademy.blog_site.entity.Blog;
import lt.codeacademy.blog_site.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog saveBlog(Blog blog){
        return blogRepository.save(blog);
    }

    public List<Blog> getAll(){
        return blogRepository.findAll();
    }
}
