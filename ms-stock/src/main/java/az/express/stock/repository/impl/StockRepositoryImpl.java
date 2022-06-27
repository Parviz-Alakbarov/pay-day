package az.express.stock.repository.impl;

import az.express.stock.model.Stock;
import az.express.stock.repository.StockRepository;
import az.express.stock.util.DefaultValueUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final RedissonClient redissonClient;

    @Override
    public void saveStock(List<Stock> stockList) {
        redissonClient.getList(DefaultValueUtil.STOCK_BUCKET_NAME).clear();
        redissonClient.getList(DefaultValueUtil.STOCK_BUCKET_NAME).addAll(stockList);
    }

    @Override
    public List<Stock> getStocks() {
        return redissonClient.getList(DefaultValueUtil.STOCK_BUCKET_NAME);
    }
}
