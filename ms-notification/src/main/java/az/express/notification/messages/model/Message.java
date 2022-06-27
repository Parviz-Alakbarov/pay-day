package az.express.notification.messages.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
    private Long id;
    private String type;
    private T data;

    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
    Class<T> hits;

    public Message(T data) {
        this.data = data;
    }

}
