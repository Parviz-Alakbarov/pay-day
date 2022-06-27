package az.express.stock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private Long orderId;
    private Long userId;
    private Long stockId;
    private String accountNumber;
    private BigDecimal targetPrice;
    private OrderType orderType;
    private Integer quantity;
    private OrderState orderState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return orderId.equals(orderDto.orderId) && stockId.equals(orderDto.stockId) && accountNumber.equals(orderDto.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, stockId, accountNumber);
    }
}
