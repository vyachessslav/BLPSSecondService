package gmail.vezhur2003.blps.repository;

import gmail.vezhur2003.blps.DTO.NotificationData;
import gmail.vezhur2003.blps.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Transactional
    void deleteAllByEmail(String email);

    @Transactional
    @Query(nativeQuery = true, value = "select distinct email from subscription")
    List<String> findEmails();

    @Transactional
    @Query(nativeQuery = true, value = "select distinct tag from subscription where email = :email")
    List<String> findTagsByEmail(String email);

    @Transactional
    int deleteAllByEmailAndTag(String email, String tag);
}
