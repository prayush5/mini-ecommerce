package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.entity.CartItem;
import com.project.online_book_store.entity.OrderItem;
import com.project.online_book_store.entity.Orders;
import com.project.online_book_store.repository.CartItemRepository;
import com.project.online_book_store.repository.OrderItemRepository;
import com.project.online_book_store.repository.OrderRepository;
import com.project.online_book_store.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void checkout(int userId){
        List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
        if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        Orders orders = new Orders();
        orders.setUser(cartItems.get(0).getUser());
        orders.setOrderDate(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems){
            OrderItem item = new OrderItem();
            item.setOrders(orders);
            item.setUser(cartItem.getUser());
            item.setBook(cartItem.getBook());
            item.setMedicine(cartItem.getMedicine());
            item.setQuantity(cartItem.getQuantity());

            BigDecimal price = cartItem.getBook() != null ?
                    cartItem.getBook().getPrice() :
                    cartItem.getMedicine().getPrice();

            item.setPrice(price.multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            total = total.add(item.getPrice());
            orderItems.add(item);

        }
        orders.setTotalAmount(total);
        orders.setOrderItems(orderItems);
        orderRepository.save(orders);
        cartItemRepository.deleteAll(cartItems);
    }
}
