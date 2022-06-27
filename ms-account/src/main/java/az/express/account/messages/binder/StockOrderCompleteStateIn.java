package az.express.account.messages.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface StockOrderCompleteStateIn {

    String INPUT = "stockOrderCompleteStateInput";

    @Input(StockOrderCompleteStateIn.INPUT)
    SubscribableChannel input();

}
