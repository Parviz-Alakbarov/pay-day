package az.express.order.messages;

import az.express.order.messages.binder.NotificationStateOut;
import az.express.order.messages.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(NotificationStateOut.class)
public class NotificationMessageSender<T> {

    private final MessageChannel output;
    private final ObjectMapper objectMapper;

    public NotificationMessageSender(@Qualifier("notificationStateOutput") MessageChannel output,
                                     ObjectMapper objectMapper) {
        this.output = output;
        this.objectMapper = objectMapper;
    }

    public void send(Message<T> message) {
        try {
            var messageJson = objectMapper.writeValueAsString(message);

            log.info("STREAM MESSAGE SENT: {}", messageJson);

            output.send(MessageBuilder.withPayload(messageJson).build());

        } catch (Exception e) {
            throw new RuntimeException("Could not transform and send message due to: " + e.getMessage(), e);
        }
    }
}