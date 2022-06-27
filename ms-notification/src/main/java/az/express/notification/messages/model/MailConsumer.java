package az.express.notification.messages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailConsumer {

    private Long userId;
    private String message;

    public static MailConsumer of(Long userId, String message) {
        return new MailConsumer(userId, message);
    }
}
