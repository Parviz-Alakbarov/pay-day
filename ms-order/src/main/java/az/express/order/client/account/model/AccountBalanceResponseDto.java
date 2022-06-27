package az.express.order.client.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceResponseDto {

    private BigDecimal balance;

    public static AccountBalanceResponseDto of(BigDecimal balance) {
        return new AccountBalanceResponseDto(balance);
    }
}
