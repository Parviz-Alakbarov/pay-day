package az.express.order.messages;

import az.express.order.messages.model.CompleteOrderConsumer;
import az.express.order.messages.model.Message;
import az.express.order.messages.binder.OrderCompleteStateIn;
import az.express.order.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OrderCompleteStateIn.class)
@RequiredArgsConstructor
@Slf4j
public class MessageListener {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;


    @StreamListener(target = OrderCompleteStateIn.INPUT)
    public void handle(String messageJson) throws Exception {
        log.info("Consuming complete order...{}", messageJson);

        Message<CompleteOrderConsumer> message = objectMapper.readValue(messageJson, new TypeReference<>() {
        });

        orderService.completeOrder(message.getData());
    }

}
