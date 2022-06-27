package az.express.stock.messages.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface StockOrderCompleteStateOut {

    String OUTPUT = "stockOrderCompleteStateOutput";

    @Output(StockOrderCompleteStateOut.OUTPUT)
    SubscribableChannel output();

}
