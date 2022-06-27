package az.express.stock.repository;

import az.express.stock.model.Stock;

import java.util.List;

public interface StockRepository {

    void saveStock(List<Stock> stockList);

    List<Stock> getStocks();

}
