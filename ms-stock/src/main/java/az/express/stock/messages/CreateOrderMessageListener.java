package az.express.stock.messages;

import az.express.stock.repository.OrderRepository;
import az.express.stock.messages.binder.OrderCreateStateIn;
import az.express.stock.messages.model.NotificationProducer;
import az.express.stock.model.OrderDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OrderCreateStateIn.class)
@RequiredArgsConstructor
@Slf4j
public class CreateOrderMessageListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;


    @StreamListener(target = OrderCreateStateIn.INPUT)
    public void handle(String messageJson) throws Exception {
        log.info("Consuming create order.. {}", messageJson);

        NotificationProducer<OrderDto> notificationProducer = objectMapper.readValue(messageJson, new TypeReference<>() {
        });

        var orderDto = notificationProducer.getData();
        orderDto.setOrderId(notificationProducer.getId());

        orderRepository.saveOrder(orderDto);
    }

}
