package az.express.account.repository;

import az.express.account.model.entity.Stock;

import java.util.List;

public interface StockRepository {

    List<Stock> getStocks();

}
