package az.express.account.messages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
    private Long id;
    private T data;

    public Message(T data) {
        this.data = data;
    }
}
