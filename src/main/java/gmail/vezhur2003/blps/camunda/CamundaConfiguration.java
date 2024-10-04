package gmail.vezhur2003.blps.camunda;

import gmail.vezhur2003.blps.DTO.SubscriptionData;
import gmail.vezhur2003.blps.DTO.VacancyData;
import gmail.vezhur2003.blps.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CamundaConfiguration {

    private final SubscriptionService subscriptionService;

    private final TransactionTemplate transactionTemplate;

    @Bean
    @ExternalTaskSubscription(value = "subscribe", variableNames = {"email", "tag"})
    public ExternalTaskHandler subscribeHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            String email = helper.getVarOrThrow("email");
            String tag = helper.getVarOrThrow("tag");
            subscriptionService.subscribe(new SubscriptionData(email, tag));
            externalTaskService.complete(externalTask);
        }, Optional.of(transactionTemplate));
    }

    @Bean
    @ExternalTaskSubscription(value = "unsubscribe", variableNames = {"email", "tag"})
    public ExternalTaskHandler unsubscribeHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            String email = helper.getVarOrThrow("email");
            String tag = helper.getVarOrThrow("tag");
            subscriptionService.unsubscribe(new SubscriptionData(email, tag));
            externalTaskService.complete(externalTask);
        }, Optional.of(transactionTemplate));
    }
}
