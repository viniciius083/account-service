package br.com.account.events;

import br.com.account.dto.AccountUpsertEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAccountUpsertEvent(AccountUpsertEvent event) {
        rabbitTemplate.convertAndSend("account.exchange", "account.upsert", event);
    }
}
