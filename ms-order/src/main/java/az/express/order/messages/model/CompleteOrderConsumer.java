package az.express.order.messages.model;

import az.express.order.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteOrderConsumer {

    private Long stockId;
    private String stockName;
    private Long userId;
    private String accountNumber;
    private Integer quantity;
    private Long orderId;
    private OrderType orderType;
    private BigDecimal orderPrice;

}
