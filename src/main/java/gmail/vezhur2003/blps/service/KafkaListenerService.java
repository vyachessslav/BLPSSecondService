package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.VacancyData;
import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.repository.UserRepository;
import gmail.vezhur2003.blps.entity.VacancyEntity;
import gmail.vezhur2003.blps.repository.VacancyRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class KafkaListenerService {

    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;


    @KafkaListener(topics = "${spring.kafka.topics.user}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "userListenerContainerFactory")
    public void usersListener(@Payload UserEntity userEntity) {
        System.out.println("received message: " + userEntity);
        userRepository.save(userEntity);
    }

    @KafkaListener(topics = "${spring.kafka.topics.vacancy}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "vacancyListenerContainerFactory")
    public void issuesListener(@Payload VacancyData vacancyData) {
        System.out.println("received message: " + vacancyData);
        vacancyRepository.save(new VacancyEntity(vacancyData, true));
    }
}
