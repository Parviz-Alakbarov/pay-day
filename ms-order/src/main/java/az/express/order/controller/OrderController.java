package az.express.order.controller;

import az.express.order.error.model.RestResponse;
import az.express.order.model.dto.OrderDto;
import az.express.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public RestResponse<String> createOrder(@RequestBody @Valid OrderDto order) {
        orderService.createOrder(order);
        return new RestResponse<>("Operation successfully completed");
    }

}
