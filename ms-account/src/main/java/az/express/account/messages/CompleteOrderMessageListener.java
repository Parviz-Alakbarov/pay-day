package az.express.account.messages;

import az.express.account.messages.binder.StockOrderCompleteStateIn;
import az.express.account.messages.model.AccountStockConsumer;
import az.express.account.messages.model.Message;
import az.express.account.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(StockOrderCompleteStateIn.class)
@RequiredArgsConstructor
public class CompleteOrderMessageListener {

    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    @StreamListener(target = StockOrderCompleteStateIn.INPUT)
    public void handleUpdateAccount(String messageJson) throws JsonProcessingException {
        log.info("consuming update account stock...{}", messageJson);

        Message<AccountStockConsumer> message = objectMapper.readValue(messageJson, new TypeReference<>() {
        });

        accountService.update(message.getData());
    }

}
