package gmail.vezhur2003.blps.repository;

import gmail.vezhur2003.blps.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    @Transactional
    @Query(nativeQuery = true, value = "select name from vacancy where is_new = true and topic = :topic")
    List<String> findVacancy(String topic);

    @Transactional
    @Query(nativeQuery = true, value = "update vacancy set is_new = false")
    void expireVacancy();
}
