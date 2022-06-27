package az.express.order.model.dto;

import az.express.order.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    @NotNull
    private Long stockId;
    private Long userId;
    private String accountNumber;
    private Integer quantity;
    private BigDecimal targetPrice;
    private OrderType orderType;

}
