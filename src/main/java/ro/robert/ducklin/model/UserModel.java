package ro.robert.ducklin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.rmi.server.UID;
import java.time.Instant;

@Data
@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    private UID uid;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Instant created;
    @Column(nullable = false)
    private Boolean enabled;

}
