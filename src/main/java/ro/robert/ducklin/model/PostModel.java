package ro.robert.ducklin.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "post")
public class PostModel {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long postId;

    @Column(name = "name")
    @NotBlank(message = "Post name cannot be empty or Null")
    private String postName;

    @Column(name = "url")
    @Nullable
    private String url;

    @Column(name = "description")
    @Nullable
    @Lob
    private String description;

    @Column(name = "votes")
    private Integer voteCount = 0;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private UserModel user;

    @Column(name = "createdDate")
    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private SubDucklingModel subDucklingModel;

}
