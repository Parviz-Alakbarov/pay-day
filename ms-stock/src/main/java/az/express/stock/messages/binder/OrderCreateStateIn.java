package az.express.stock.messages.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderCreateStateIn {

    String INPUT = "orderCreateStateInput";

    @Input(OrderCreateStateIn.INPUT)
    SubscribableChannel input();

}
