package az.express.stock.service.impl;

import az.express.stock.error.StockNotFoundException;
import az.express.stock.messages.CompleteStockOrderMessageSender;
import az.express.stock.messages.model.AccountStockProducer;
import az.express.stock.messages.model.NotificationProducer;
import az.express.stock.model.OrderDto;
import az.express.stock.model.Stock;
import az.express.stock.repository.OrderRepository;
import az.express.stock.service.StockService;
import az.express.stock.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final CompleteStockOrderMessageSender<AccountStockProducer> completeAccountMessageSender;


    /**
     * Getting orders, stocks from cache
     * In case of stockPrice equal or less than targetPrice then BUY order will be completed
     * In case of stockPrice equal or grater than targetPrice then SELL order will be completed
     */
    @Override
    public void completeOrder() {
        log.info("Completing orders...");
        var orders = orderRepository.getOrders();
        var stocks = stockService.getStocks();

        orders.forEach(order -> completeOrderDetail(order, getStockPrice(stocks, order.getStockId())));
    }

    private void completeOrderDetail(OrderDto order, Stock stock) {
        switch (order.getOrderType()) {
            case BUY:
                if (stock.getPrice().compareTo(order.getTargetPrice()) <= 0)
                    completeOrderOperation(order, stock);

                break;
            case SELL:
                if (stock.getPrice().compareTo(order.getTargetPrice()) >= 0)
                    completeOrderOperation(order, stock);

                break;
            default:
                log.info("Order type can not be: {},  and this order has not to come to here!", order.getOrderType());
        }
    }


    /**
     * Can be maintained order state on cache to avoid duplication
     *
     * @param order
     * @param stock
     */
    private void completeOrderOperation(OrderDto order, Stock stock) {
        if (completeOrderOnExternal()) {
            orderRepository.deleteOrder(order);

            order.setTargetPrice(stock.getPrice());
            completeAccountMessageSender.send(buildAccountStockDto(order, stock.getName()));
        }
    }

    private NotificationProducer<AccountStockProducer> buildAccountStockDto(OrderDto order, String stockName) {
        var accountStockDto = AccountStockProducer.builder()
                .accountNumber(order.getAccountNumber())
                .orderPrice(order.getTargetPrice())
                .stockId(order.getStockId())
                .stockName(stockName)
                .userId(order.getUserId())
                .orderId(order.getOrderId())
                .orderType(order.getOrderType())
                .quantity(order.getQuantity())
                .build();

        return new NotificationProducer<>(accountStockDto);
    }

    private Stock getStockPrice(List<Stock> stocks, Long stockId) {
        return stocks.stream()
                .filter(stock -> stock.getId().equals(stockId))
                .findFirst()
                .orElseThrow(StockNotFoundException::new);
    }


    /**
     * Mock endpoint like external endpoint
     *
     * @return always true
     */
    private Boolean completeOrderOnExternal() {
        return Boolean.TRUE;
    }

}
