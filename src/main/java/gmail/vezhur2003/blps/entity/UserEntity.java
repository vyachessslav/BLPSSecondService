package gmail.vezhur2003.blps.entity;

import gmail.vezhur2003.blps.security.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@ToString
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;
}
