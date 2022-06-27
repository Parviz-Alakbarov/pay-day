package az.express.stock.messages;

import az.express.stock.messages.binder.StockOrderCompleteStateOut;
import az.express.stock.messages.model.NotificationProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StockOrderCompleteStateOut.class)
@Slf4j
public class CompleteStockOrderMessageSender<T> {

    private final MessageChannel output;
    private final ObjectMapper objectMapper;

    public CompleteStockOrderMessageSender(@Qualifier("stockOrderCompleteStateOutput") MessageChannel output,
                                           ObjectMapper objectMapper) {
        this.output = output;
        this.objectMapper = objectMapper;
    }

    public void send(NotificationProducer<T> notificationProducer) {
        try {
            var messageJson = objectMapper.writeValueAsString(notificationProducer);

            log.info("STREAM MESSAGE SENT: {}", messageJson);

            output.send(MessageBuilder.withPayload(messageJson).build());

        } catch (Exception e) {
            throw new RuntimeException("Could not transform and send message due to: " + e.getMessage(), e);
        }
    }

}