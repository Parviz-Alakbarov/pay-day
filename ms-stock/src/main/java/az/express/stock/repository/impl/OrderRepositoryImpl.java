package az.express.stock.repository.impl;

import az.express.stock.repository.OrderRepository;
import az.express.stock.util.DefaultValueUtil;
import az.express.stock.model.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final RedissonClient redissonClient;

    @Override
    public void saveOrder(OrderDto order) {
        redissonClient.getList(DefaultValueUtil.ORDERS_BUCKET_NAME).add(order);
    }

    public void deleteOrder(OrderDto order) {
        redissonClient.getList(DefaultValueUtil.ORDERS_BUCKET_NAME).remove(order);
    }


    @Override
    public List<OrderDto> getOrders() {
        return redissonClient.getList(DefaultValueUtil.ORDERS_BUCKET_NAME);
    }

}
