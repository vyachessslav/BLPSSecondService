package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.NotificationData;
import gmail.vezhur2003.blps.mapper.NotificationMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final SubscriptionService subscriptionService;
    private final JavaMailSender mailSender;
    @Value(value = "${spring.mail.username}")
    private String from;

    //@Scheduled(cron = "0 0 */2 * * *")
    @Scheduled(fixedRate = 30000)
    @Async
    public void sendNotifications() {
        ArrayList<ArrayList<ArrayList<NotificationData>>> notificationList = subscriptionService.getNotifications();
        if (!notificationList.isEmpty()) {
            for (ArrayList<ArrayList<NotificationData>> notificationListByEmail : notificationList) {
                sendEmail(notificationListByEmail.get(0).get(0).getEmail(),
                        "Появились новые вакансии!",
                        NotificationMapper.toEmailBody(notificationListByEmail));
            }
            subscriptionService.expireVacancy();
        }
    }

    public void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
            log.info("Email sent to {} successfully", toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
