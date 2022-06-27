package az.express.order.messages.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderCompleteStateIn {

    String INPUT = "orderCompleteStateInput";

    @Input(OrderCompleteStateIn.INPUT)
    SubscribableChannel input();

}
