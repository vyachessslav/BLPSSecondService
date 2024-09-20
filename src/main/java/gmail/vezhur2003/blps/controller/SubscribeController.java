package gmail.vezhur2003.blps.controller;

import gmail.vezhur2003.blps.DTO.SubscriptionData;
import gmail.vezhur2003.blps.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoints.base-url}/subscription")
@CrossOrigin
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscriptionService subscriptionService;

    @PutMapping
    @SneakyThrows
    public ResponseEntity<SubscriptionData> subscribe(@RequestBody @Validated SubscriptionData subscription) {
        return ResponseEntity.ok(subscriptionService.subscribe(subscription));
    }

    @DeleteMapping
    @SneakyThrows
    public ResponseEntity<SubscriptionData> unsubscribe(@RequestBody SubscriptionData subscription) {
        return ResponseEntity.ok(subscriptionService.unsubscribe(subscription));
    }
}
