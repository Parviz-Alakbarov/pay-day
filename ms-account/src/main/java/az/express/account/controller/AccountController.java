package az.express.account.controller;

import az.express.account.error.model.RestResponse;
import az.express.account.model.dto.AccountBalanceResponseDto;
import az.express.account.model.dto.AccountDepositDto;
import az.express.account.model.dto.AccountHoldDto;
import az.express.account.model.dto.AccountRequestDto;
import az.express.account.model.dto.AccountResponseDto;
import az.express.account.model.dto.AccountStockDto;
import az.express.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;


    @PostMapping
    public RestResponse<AccountResponseDto> save(@RequestBody AccountRequestDto dto) {
        return new RestResponse<>(accountService.save(dto));
    }


    @PutMapping("/{accountNumber}/hold-amount")
    public void holdAmount(@PathVariable("accountNumber") @NotNull String accountNumber,
                           @RequestBody AccountHoldDto dto) {
        accountService.holdAmount(accountNumber, dto);
    }


    @GetMapping("/{accountNumber}/cash-balance")
    public RestResponse<AccountBalanceResponseDto> getAccountCashBalance(@PathVariable("accountNumber") String accountNumber) {
        return new RestResponse<>(accountService.getAccountCashBalance(accountNumber));
    }


    @GetMapping("/{accountNumber}/total-balance")
    public RestResponse<AccountBalanceResponseDto> getAccountTotalBalance(@PathVariable("accountNumber") String accountNumber) {
        return new RestResponse<>(accountService.getAccountTotalBalance(accountNumber));
    }


    @PutMapping("/{accountNumber}/deposit")
    public void depositAccount(@PathVariable("accountNumber") @NotNull String accountNumber,
                               @RequestBody AccountDepositDto dto) {
        accountService.depositAccount(accountNumber, dto);
    }


    @GetMapping("/{accountNumber}/stocks")
    public List<AccountStockDto> getAccountStocks(@PathVariable("accountNumber") String accountNumber) {
        return accountService.getAccountStocks(accountNumber);
    }

}
