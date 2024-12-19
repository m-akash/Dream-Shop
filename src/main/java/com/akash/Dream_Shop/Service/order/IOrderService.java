package com.akash.Dream_Shop.Service.order;

import com.akash.Dream_Shop.DTO.OrderDto;
import com.akash.Dream_Shop.Model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
