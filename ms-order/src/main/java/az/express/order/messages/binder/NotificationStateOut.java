package az.express.order.messages.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationStateOut {

    String OUTPUT = "notificationStateOutput";

    @Output(NotificationStateOut.OUTPUT)
    SubscribableChannel output();

}
