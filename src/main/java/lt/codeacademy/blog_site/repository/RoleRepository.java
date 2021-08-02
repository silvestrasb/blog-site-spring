package lt.codeacademy.blog_site.repository;

import lt.codeacademy.blog_site.entity.Comment;
import lt.codeacademy.blog_site.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByName(String name);
}
