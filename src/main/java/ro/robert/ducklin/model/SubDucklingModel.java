package ro.robert.ducklin.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Table(name = "subduckling")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubDucklingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Instant createdDate;
    @OneToMany(fetch = LAZY)
    private List<PostModel> posts;
    @ManyToOne(fetch = LAZY)
    private UserModel userModel;
}
