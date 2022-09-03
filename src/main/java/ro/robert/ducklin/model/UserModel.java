package ro.robert.ducklin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String uid;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @Email
    @Column(nullable = false)
    @NotEmpty(message = "Email is required")
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private Instant created;

    @Column(nullable = false)
    private Boolean enabled;

}
