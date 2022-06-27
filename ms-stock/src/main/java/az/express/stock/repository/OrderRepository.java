package az.express.stock.repository;

import az.express.stock.model.OrderDto;

import java.util.List;

public interface OrderRepository {

    void saveOrder(OrderDto order);

    void deleteOrder(OrderDto order);

    List<OrderDto> getOrders();

}
