package az.express.order.service.impl;

import az.express.order.mapper.OrderMapper;
import az.express.order.messages.NotificationMessageSender;
import az.express.order.messages.OrderMessageSender;
import az.express.order.messages.model.CompleteOrderConsumer;
import az.express.order.messages.model.Message;
import az.express.order.messages.model.OrderNotificationMessage;
import az.express.order.service.OrderService;
import az.express.order.client.account.model.AccountStock;
import az.express.order.error.InsufficientBalanceException;
import az.express.order.error.OrderNotFoundException;
import az.express.order.model.OrderState;
import az.express.order.model.OrderType;
import az.express.order.model.dto.OrderDto;
import az.express.order.model.entity.Order;
import az.express.order.repository.OrderRepository;
import az.express.order.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;

import static az.express.order.util.DefaultValueUtil.ORDER_MESSAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMessageSender<OrderDto> orderMessageSender;
    private final AccountService accountService;
    private final OrderMapper orderMapper;
    private final NotificationMessageSender<OrderNotificationMessage> notificationSender;


    @Override
    public void createOrder(OrderDto dto) {
        validateOrder(dto);

        Order orderEntity = orderMapper.toOrder(dto);
        Order savedOrder = orderRepository.save(orderEntity);

        accountService.holdAccountAmount(dto);

        Message<OrderDto> createOrderMessage = new Message<>(savedOrder.getId(), dto);
        orderMessageSender.send(createOrderMessage);
    }


    @Override
    @Transactional
    public void completeOrder(CompleteOrderConsumer consumer) {
        var order = orderRepository.findById(consumer.getOrderId())
                .orElseThrow(OrderNotFoundException::new);

        order.setOrderState(OrderState.COMPLETED);

        orderRepository.save(order);

        notificationSender.send(getOrderNotificationMessage(consumer));
    }


    private void validateOrder(OrderDto dto) {
        switch (dto.getOrderType()) {
            case BUY:
                Optional.of(dto)
                        .filter(order -> order.getOrderType() == OrderType.BUY)
                        .filter(this::checkBalance)
                        .orElseThrow(() -> new InsufficientBalanceException("You don not have enough account cash balance to make such an order"));
                break;

            case SELL:
                accountService.getAccountStocks(dto.getAccountNumber()).stream()
                        .filter(accountStock -> accountStock.getStockId().equals(dto.getStockId()))
                        .map(AccountStock::getQuantity)
                        .filter(stockQuantity -> stockQuantity > dto.getQuantity())
                        .findFirst()
                        .orElseThrow(() -> new InsufficientBalanceException("You do not have enough stock in your account balance"));
                break;
            default:
                log.warn("There is not such a order: " + dto.getOrderType());

        }
    }

    private Boolean checkBalance(OrderDto order) {
        return order.getTargetPrice()
                .compareTo(accountService.getAccountBalance(order.getAccountNumber())) <= 0;
    }


    private Message<OrderNotificationMessage> getOrderNotificationMessage(CompleteOrderConsumer completeOrderConsumer) {
        String textMessage = MessageFormat.format(ORDER_MESSAGE,
                completeOrderConsumer.getQuantity(),
                completeOrderConsumer.getStockName(),
                completeOrderConsumer.getOrderPrice(),
                completeOrderConsumer.getOrderType().getMessageAz());

        var orderMessage = OrderNotificationMessage.of(completeOrderConsumer.getUserId(), textMessage);

        return new Message<>(orderMessage);
    }


}