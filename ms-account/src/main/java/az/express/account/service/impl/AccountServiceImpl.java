package az.express.account.service.impl;

import az.express.account.messages.CompleteOrderMessageSender;
import az.express.account.messages.model.AccountStockConsumer;
import az.express.account.messages.model.CompleteOrderProducer;
import az.express.account.messages.model.Message;
import az.express.account.repository.AccountRepository;
import az.express.account.service.AccountService;
import az.express.account.service.StockService;
import az.express.account.error.AccountNotFoundException;
import az.express.account.error.InsufficientBalanceException;
import az.express.account.mapper.AccountMapper;
import az.express.account.model.dto.AccountBalanceResponseDto;
import az.express.account.model.dto.AccountDepositDto;
import az.express.account.model.dto.AccountHoldDto;
import az.express.account.model.dto.AccountRequestDto;
import az.express.account.model.dto.AccountResponseDto;
import az.express.account.model.dto.AccountStockDto;
import az.express.account.model.entity.Account;
import az.express.account.model.entity.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final StockService stockService;
    private final AccountMapper accountMapper;
    private final CompleteOrderMessageSender<CompleteOrderProducer> completeOrderMessageSender;


    @Override
    public AccountResponseDto save(AccountRequestDto dto) {
        var account = Account.builder()
                .userId(dto.getUserId())
                .accountNumber(generateAccount())
                .build();

        var createdAccount = accountRepository.save(account);

        return accountMapper.toAccountResponseDto(createdAccount);
    }


    @Override
    @Transactional
    public void update(AccountStockConsumer consumer) {
        var account = accountRepository.findByAccountNumber(consumer.getAccountNumber())
                .orElseThrow(AccountNotFoundException::new);

        updateAccount(account, consumer);

        CompleteOrderProducer producer = accountMapper.toCompleteOrderProducer(consumer);

        completeOrderMessageSender.send(new Message<>(producer));
    }


    @Override
    public void holdAmount(String accountNumber, AccountHoldDto dto) {

        var account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new);

        if (account.getBalance().compareTo(dto.getHoldAmount()) >= 0) {

            account.setHoldAmount(Optional.ofNullable(account.getHoldAmount()).orElse(ZERO)
                    .add(dto.getHoldAmount()));
            accountRepository.save(account);

        } else {
            throw new InsufficientBalanceException("There is not enough balance in your account");
        }
    }


    @Override
    public AccountBalanceResponseDto getAccountCashBalance(String accountNumber) {
        BigDecimal balance = accountRepository.findByAccountNumber(accountNumber)
                .map(account -> Optional.ofNullable(account.getBalance()).orElse(ZERO)
                        .subtract(Optional.ofNullable(account.getHoldAmount()).orElse(ZERO)))
                .orElseThrow(AccountNotFoundException::new);

        return AccountBalanceResponseDto.of(balance);
    }


    @Override
    public List<AccountStockDto> getAccountStocks(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(Account::getAccountStocks)
                .map(accountMapper::toAccountStockDtoList)
                .orElse(Collections.emptyList());
    }

    @Override
    public AccountBalanceResponseDto getAccountTotalBalance(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(account -> {
                    BigDecimal accountCashBalance = Optional.ofNullable(account.getBalance()).orElse(ZERO);
                    BigDecimal accountHoldAmount = Optional.ofNullable(account.getHoldAmount()).orElse(ZERO);
                    var cashBalance = accountCashBalance.subtract(accountHoldAmount);
//                    var stockBalance = getAccountRealTimeBalance(account);
                    return AccountBalanceResponseDto.of(cashBalance.add(ZERO));
                })
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void depositAccount(String accountNumber, AccountDepositDto dto) {
        var account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new);

        account.setBalance(Optional.ofNullable(account.getBalance())
                .orElse(ZERO)
                .add(dto.getDepositAmount()));

        accountRepository.save(account);
    }

    private BigDecimal getAccountRealTimeBalance(Account account) {
        List<Stock> stocks = stockService.getStocks();

        return account.getAccountStocks().stream()
                .map(accountStock -> new BigDecimal(accountStock.getQuantity())
                        .multiply(getStockPrice(stocks, accountStock.getStockId())))
                .reduce(BigDecimal::add)
                .orElse(ZERO);
    }

    private BigDecimal getStockPrice(List<Stock> stocks, Long stockId) {
        return stocks.stream()
                .filter(stock -> stock.getId().equals(stockId))
                .map(Stock::getPrice)
                .findFirst()
                .orElse(ZERO);
    }

    private void updateAccount(Account account, AccountStockConsumer dto) {
        BigDecimal accountBalance = Optional.ofNullable(account.getBalance()).orElse(ZERO);
        BigDecimal accountHoldAmount = Optional.ofNullable(account.getHoldAmount()).orElse(ZERO);
        BigDecimal totalOrderAmount = dto.getOrderPrice().multiply(new BigDecimal(dto.getQuantity()));

        if (CollectionUtils.isEmpty(account.getAccountStocks())) {
            var accountStock = accountMapper.toAccountStock(dto);
            accountStock.setAccount(account);

            account.addAccountStock(accountStock);
        }

        switch (dto.getOrderType()) {
            case BUY:
                account.setBalance(accountBalance.subtract(totalOrderAmount));
                account.setHoldAmount(accountHoldAmount.subtract(totalOrderAmount));

                account.getAccountStocks().stream()
                        .filter(item -> item.getStockId().equals(dto.getStockId()))
                        .forEach(stock -> stock.setQuantity(stock.getQuantity() + dto.getQuantity()));

                accountRepository.save(account);
                break;

            case SELL:
                account.setBalance(accountBalance.add(totalOrderAmount));

                account.getAccountStocks().stream()
                        .filter(item -> item.getStockId().equals(dto.getStockId()))
                        .forEach(stock -> stock.setQuantity(stock.getQuantity() - dto.getQuantity()));

                accountRepository.save(account);
                break;

            default:
                log.warn("Incorrect orderType: {}", dto.getOrderType());
        }
    }


    private String generateAccount() {
        return "38810" + System.nanoTime();
    }
}
