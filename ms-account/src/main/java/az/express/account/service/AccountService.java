package az.express.account.service;

import az.express.account.messages.model.AccountStockConsumer;
import az.express.account.model.dto.AccountBalanceResponseDto;
import az.express.account.model.dto.AccountDepositDto;
import az.express.account.model.dto.AccountHoldDto;
import az.express.account.model.dto.AccountRequestDto;
import az.express.account.model.dto.AccountResponseDto;
import az.express.account.model.dto.AccountStockDto;

import java.util.List;

public interface AccountService {

    AccountResponseDto save(AccountRequestDto requestDto);

    void update(AccountStockConsumer dto);

    void holdAmount(String accountNumber, AccountHoldDto dto);

    AccountBalanceResponseDto getAccountCashBalance(String accountNumber);

    List<AccountStockDto> getAccountStocks(String accountNumber);

    AccountBalanceResponseDto getAccountTotalBalance(String accountNumber);

    void depositAccount(String accountNumber, AccountDepositDto dto);
}
