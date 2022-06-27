package az.express.account.messages.model;

import az.express.account.model.dto.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteOrderProducer {

    private Long stockId;
    private String stockName;
    private Long userId;
    private String accountNumber;
    private Integer quantity;
    private Long orderId;
    private OrderType orderType;
    private BigDecimal orderPrice;

}
