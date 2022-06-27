package az.express.order.client.account;

import az.express.order.client.account.model.AccountBalanceResponseDto;
import az.express.order.client.account.model.AccountHoldDto;
import az.express.order.client.account.model.AccountStock;
import az.express.order.error.model.RestResponse;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(
        name = "ms-account",
        configuration = MsAccountClient.FeignConfiguration.class,
        primary = false)
public interface MsAccountClient {

    @GetMapping("/accounts/{accountNumber}/cash-balance")
    RestResponse<AccountBalanceResponseDto> getAccountCashBalance(@PathVariable String accountNumber);

    @PutMapping("/accounts/{accountNumber}/hold-amount")
    void holdAmount(@PathVariable("accountNumber") @NotNull String accountNumber,
                    @RequestBody AccountHoldDto dto);

    @GetMapping("/accounts/{accountNumber}/stocks")
    List<AccountStock> getAccountStocks(@PathVariable("accountNumber") @NotNull String accountNumber);

    class FeignConfiguration {

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

    }
}
