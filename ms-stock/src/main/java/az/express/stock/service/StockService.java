package az.express.stock.service;

import az.express.stock.model.Stock;

import java.util.List;

public interface StockService {

    void updateStockPrice();

    List<Stock> getStocks();

}
