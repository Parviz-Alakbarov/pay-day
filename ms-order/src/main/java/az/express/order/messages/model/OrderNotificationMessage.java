package az.express.order.messages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderNotificationMessage {

    private Long userId;
    private String message;

    public static OrderNotificationMessage of(Long userId, String message) {
        return new OrderNotificationMessage(userId, message);
    }
}
