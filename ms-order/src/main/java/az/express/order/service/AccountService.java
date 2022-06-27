package az.express.order.service;

import az.express.order.client.account.model.AccountStock;
import az.express.order.model.dto.OrderDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    void holdAccountAmount(OrderDto orderDto);

    BigDecimal getAccountBalance(String accountNumber);

    List<AccountStock> getAccountStocks(String accountNumber);

}
