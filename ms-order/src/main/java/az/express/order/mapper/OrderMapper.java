package az.express.order.mapper;

import az.express.order.model.dto.OrderDto;
import az.express.order.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class OrderMapper {

    @Mapping(target = "orderState", constant = "PENDING")
    public abstract Order toOrder(OrderDto dto);

}
