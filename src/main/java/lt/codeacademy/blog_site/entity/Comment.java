package lt.codeacademy.blog_site.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "blog_id")
    private Long blogId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
