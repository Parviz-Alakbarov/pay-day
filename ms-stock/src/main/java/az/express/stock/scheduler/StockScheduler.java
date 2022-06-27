package az.express.stock.scheduler;

import az.express.stock.service.OrderService;
import az.express.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class StockScheduler {

    private final StockService stockService;
    private final OrderService orderService;

    /**
     * Update real-time stock price on redis cache by calling external service
     * Trigger complete order.
     */
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedDelayTask() {
        log.info("Scheduler starting...");
        stockService.updateStockPrice();

        orderService.completeOrder();
    }
}
