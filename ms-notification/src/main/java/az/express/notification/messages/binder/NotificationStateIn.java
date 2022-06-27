package az.express.notification.messages.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationStateIn {

    String INPUT = "notificationStateInput";

    @Input(NotificationStateIn.INPUT)
    SubscribableChannel input();

}
