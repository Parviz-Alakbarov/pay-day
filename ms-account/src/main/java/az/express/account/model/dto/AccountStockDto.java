package az.express.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountStockDto {

    private Long stockId;
    private String accountNumber;
    private Integer quantity;
    private Long orderId;
    private OrderType orderType;
    private BigDecimal orderPrice;

}
