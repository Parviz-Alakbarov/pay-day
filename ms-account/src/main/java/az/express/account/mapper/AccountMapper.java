package az.express.account.mapper;

import az.express.account.messages.model.AccountStockConsumer;
import az.express.account.messages.model.CompleteOrderProducer;
import az.express.account.model.dto.AccountResponseDto;
import az.express.account.model.dto.AccountStockDto;
import az.express.account.model.entity.Account;
import az.express.account.model.entity.AccountStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AccountMapper {

    public abstract AccountResponseDto toAccountResponseDto(Account account);


    @Mapping(target = "account.accountNumber", source = "accountNumber")
    public abstract AccountStock toAccountStock(AccountStockConsumer dto);


    @Mapping(target = "accountNumber", source = "account.accountNumber")
    public abstract AccountStockDto toAccountStockDto(AccountStock accountStock);

    public abstract List<AccountStockDto> toAccountStockDtoList(List<AccountStock> accountStocks);

    public abstract CompleteOrderProducer toCompleteOrderProducer(AccountStockConsumer accountStockConsumer);

}
