package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.repository.OrderRepository;
import com.project.online_book_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
}
