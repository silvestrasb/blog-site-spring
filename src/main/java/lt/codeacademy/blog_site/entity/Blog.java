package lt.codeacademy.blog_site.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Size(min = 1, max = 40)
    private String title;

    @Column(name = "content")
    @Size(min = 6, max = 25000)
    private String content;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private List<Comment> comments;

}
