package az.express.stock.messages.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationProducer<T> {
    private Long id;
    private T data;

    public NotificationProducer(T data) {
        this.data = data;
    }
}
