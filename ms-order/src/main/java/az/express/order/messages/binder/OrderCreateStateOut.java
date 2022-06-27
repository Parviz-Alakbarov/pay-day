package az.express.order.messages.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface OrderCreateStateOut {

    String OUTPUT = "orderCreateStateOutput";

    @Output(OrderCreateStateOut.OUTPUT)
    SubscribableChannel output();

}
