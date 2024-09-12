package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.UserData;
import gmail.vezhur2003.blps.DTO.VacancyData;
import gmail.vezhur2003.blps.primary.UserEntity;
import gmail.vezhur2003.blps.primary.UserRepository;
import gmail.vezhur2003.blps.secondary.VacancyEntity;
import gmail.vezhur2003.blps.secondary.VacancyRepository;
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
    private final UserService userService;
    private final VacancyService vacancyService;

    @KafkaListener(topics = "${spring.kafka.topics.user}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "userListenerContainerFactory")
    public void usersListener(@Payload UserEntity userEntity) {
        System.out.println("received message: " + userEntity);
        userRepository.save(userEntity);
    }

    @KafkaListener(topics = "${spring.kafka.topics.issue}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "issueListenerContainerFactory")
    public void issuesListener(@Payload VacancyEntity vacancyEntity) {
        System.out.println("received message: " + vacancyEntity);
        vacancyRepository.save(vacancyEntity);
    }
}
