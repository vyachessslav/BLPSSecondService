package gmail.vezhur2003.blps.entity;

import gmail.vezhur2003.blps.DTO.SubscriptionData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "tag"})})
@Getter
@Setter
@RequiredArgsConstructor
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private String email;

    public SubscriptionEntity(SubscriptionData subscriptionData) {
        this.email = subscriptionData.getEmail();
        this.tag = subscriptionData.getTag();
    }
}
