package ro.robert.ducklin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
public class VerificationTokenModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String token;

    @OneToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    private UserModel user;

    @Column
    private Instant expiryDate;
}
