package az.express.account.repository.impl;

import az.express.account.model.entity.Stock;
import az.express.account.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.util.List;

import static az.express.account.util.DefaultValueUtil.STOCK_BUCKET_NAME;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final RedissonClient redissonClient;

    @Override
    public List<Stock> getStocks() {
        return redissonClient.getList(STOCK_BUCKET_NAME);
    }

}
