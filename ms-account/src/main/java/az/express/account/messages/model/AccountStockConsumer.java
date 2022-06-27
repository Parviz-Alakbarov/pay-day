package az.express.account.messages.model;

import az.express.account.model.dto.OrderType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountStockConsumer {

    private Long stockId;
    private String stockName;
    private Long userId;
    private String accountNumber;
    private Integer quantity;
    private Long orderId;
    private OrderType orderType;
    private BigDecimal orderPrice;

}
