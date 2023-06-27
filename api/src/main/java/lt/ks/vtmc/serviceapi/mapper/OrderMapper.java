package lt.ks.vtmc.serviceapi.mapper;

import lt.ks.vtmc.serviceapi.order.Order;
import lt.ks.vtmc.serviceapi.order.CreateOrderRequest;
import lt.ks.vtmc.serviceapi.rest.dto.OrderDto;

public interface OrderMapper {

    Order toOrder(CreateOrderRequest createOrderRequest);

    OrderDto toOrderDto(Order order);
}