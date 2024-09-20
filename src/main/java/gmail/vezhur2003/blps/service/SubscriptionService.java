package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.NotificationData;
import gmail.vezhur2003.blps.DTO.SubscriptionData;
import gmail.vezhur2003.blps.entity.SubscriptionEntity;
import gmail.vezhur2003.blps.repository.SubscriptionRepository;
import gmail.vezhur2003.blps.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    private final VacancyRepository vacancyRepository;

    @Transactional
    public SubscriptionData subscribe(SubscriptionData subscriptionData) {
        SubscriptionEntity subscription = new SubscriptionEntity(subscriptionData);
        try {
            subscriptionRepository.save(subscription);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Вы уже подписались на тег " + subscriptionData.getTag());
        }
        return subscriptionData;
    }

    @Transactional
    public SubscriptionData unsubscribe(SubscriptionData subscriptionData) {
        if (subscriptionData.getTag() == null) {
            subscriptionRepository.deleteAllByEmail(subscriptionData.getEmail());
        }
        else {
            subscriptionRepository.deleteAllByEmailAndTag(subscriptionData.getEmail(), subscriptionData.getTag());
        }
        return subscriptionData;
    }

    public ArrayList<ArrayList<ArrayList<NotificationData>>> getNotifications() {
        ArrayList<ArrayList<ArrayList<NotificationData>>> notificationsList = new ArrayList<>();
        for (String email: subscriptionRepository.findEmails()) {
            ArrayList<ArrayList<NotificationData>> notificationsByEmailList = new ArrayList<>();
            for (String tag: subscriptionRepository.findTagsByEmail(email)) {
                ArrayList<NotificationData> notificationsByTagList = new ArrayList<>();
                for (String name: vacancyRepository.findVacancy(tag)) {
                    notificationsByTagList.add(new NotificationData(email, tag, name));
                }
                if (!notificationsByTagList.isEmpty()) {
                    notificationsByEmailList.add(notificationsByTagList);
                }
            }
            if (!notificationsByEmailList.isEmpty()) {
                notificationsList.add(notificationsByEmailList);
            }
        }
        return notificationsList;
    }

    public void expireVacancy() {
        vacancyRepository.expireVacancy();
    }
}
