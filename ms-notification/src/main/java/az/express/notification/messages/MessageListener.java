package az.express.notification.messages;

import az.express.notification.util.DefaultValueUtil;
import az.express.notification.messages.binder.NotificationStateIn;
import az.express.notification.messages.model.MailConsumer;
import az.express.notification.messages.model.Message;
import az.express.notification.service.MailService;
import az.express.notification.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(NotificationStateIn.class)
@RequiredArgsConstructor
@Slf4j
public class MessageListener {

    private final ObjectMapper objectMapper;
    private final MailService mailService;
    private final UserService userService;

    @StreamListener(target = NotificationStateIn.INPUT)
    public void handle(String messageJson) throws JsonProcessingException {
        log.info("Consuming complete order notification...{}", messageJson);

        Message<MailConsumer> message = objectMapper.readValue(messageJson, new TypeReference<>() {
        });

        var userDto = userService.findById(message.getData().getUserId());

        mailService.sendMail(userDto.getEmail(), DefaultValueUtil.ORDER_COMPLETION_SUBJECT, message.getData().getMessage());
    }

}
