package az.express.account.messages.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface OrderCompleteStateOut {

    String OUTPUT = "orderCompleteStateOutput";

    @Output(OrderCompleteStateOut.OUTPUT)
    SubscribableChannel output();

}
