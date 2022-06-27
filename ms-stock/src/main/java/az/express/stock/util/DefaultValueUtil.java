package az.express.stock.util;

import java.math.BigDecimal;

public interface DefaultValueUtil {

    BigDecimal DEFAULT_PRICE_MIN = BigDecimal.valueOf(800);
    BigDecimal DEFAULT_PRICE_MAX = BigDecimal.valueOf(900);

    Long TESLA_ID = 1L;
    String TESLA_NAME = "TESLA";
    Long BITCOIN_ID = 2L;
    String BITCOIN_NAME = "BITCOIN";

    String ORDERS_BUCKET_NAME = "ordersList";
    String STOCK_BUCKET_NAME = "stockList";

}
