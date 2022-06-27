package az.express.stock.service.impl;

import az.express.stock.model.Stock;
import az.express.stock.repository.StockRepository;
import az.express.stock.service.StockService;
import az.express.stock.util.DefaultValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {


    private final StockRepository stockRepository;

    /**
     * Getting random stocks price and saving to cache as a real-time stock prices
     */
    @Override
    public void updateStockPrice() {
        var tesla = getRealTimeStockPrice(DefaultValueUtil.TESLA_ID, DefaultValueUtil.TESLA_NAME);
        var bitcoin = getRealTimeStockPrice(DefaultValueUtil.BITCOIN_ID, DefaultValueUtil.BITCOIN_NAME);

        stockRepository.saveStock(List.of(tesla, bitcoin));
    }

    @Override
    public List<Stock> getStocks() {
        return stockRepository.getStocks();
    }


    private Stock getRealTimeStockPrice(long stockId, String stockName) {
        return Stock.builder()
                .id(stockId)
                .name(stockName)
                .price(generateRandomBigDecimalFromRange())
                .build();
    }

    private static BigDecimal generateRandomBigDecimalFromRange() {
        var randomBigDecimal = DefaultValueUtil.DEFAULT_PRICE_MIN
                .add(BigDecimal.valueOf(Math.random())
                        .multiply(DefaultValueUtil.DEFAULT_PRICE_MAX.subtract(DefaultValueUtil.DEFAULT_PRICE_MIN)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_EVEN);
    }
}
