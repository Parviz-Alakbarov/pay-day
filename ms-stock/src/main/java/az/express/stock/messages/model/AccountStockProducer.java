package az.express.stock.messages.model;

import az.express.stock.model.OrderType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountStockProducer {

    private Long stockId;
    private String stockName;
    private Long userId;
    private String accountNumber;
    private Integer quantity;
    private Long orderId;
    private OrderType orderType;
    private BigDecimal orderPrice;

}
