package br.com.account.messaging;

import br.com.account.dto.AccountDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueuesProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendAccountUpsertEvent(AccountDTO event) {
        rabbitTemplate.convertAndSend(Queues.ACCOUNT_UPSERT.getValue(), "#", event);
    }
}
