package az.express.order.service.impl;

import az.express.order.client.account.model.AccountHoldDto;
import az.express.order.client.account.MsAccountClient;
import az.express.order.client.account.model.AccountStock;
import az.express.order.model.OrderType;
import az.express.order.model.dto.OrderDto;
import az.express.order.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final MsAccountClient msAccountClient;

    @Override
    public void holdAccountAmount(OrderDto dto) {
        if (dto.getOrderType() == OrderType.BUY) {
            var holdAmount = dto.getTargetPrice().multiply(new BigDecimal(dto.getQuantity()));

            msAccountClient.holdAmount(dto.getAccountNumber(), AccountHoldDto.of(holdAmount));
        }
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        return msAccountClient.getAccountCashBalance(accountNumber).getData().getBalance();
    }

    @Override
    public List<AccountStock> getAccountStocks(String accountNumber) {
        return msAccountClient.getAccountStocks(accountNumber);
    }
}
