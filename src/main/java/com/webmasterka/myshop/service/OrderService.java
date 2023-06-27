package com.webmasterka.myshop.service;

import com.webmasterka.myshop.Cart;
import com.webmasterka.myshop.dto.OrderDto;
import com.webmasterka.myshop.mapper.OrderMapper;
import com.webmasterka.myshop.model.order.Order;
import com.webmasterka.myshop.model.order.OrderItem;
import com.webmasterka.myshop.repository.order.OrderItemRepository;
import com.webmasterka.myshop.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.cleanCart();
    }
}

