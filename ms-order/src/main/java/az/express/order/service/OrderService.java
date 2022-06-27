package az.express.order.service;

import az.express.order.messages.model.CompleteOrderConsumer;
import az.express.order.model.dto.OrderDto;

public interface OrderService {

    void createOrder(OrderDto orderDto);

    void completeOrder(CompleteOrderConsumer consumer);

}
