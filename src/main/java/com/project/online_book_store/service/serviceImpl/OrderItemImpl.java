package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.repository.OrderItemRepository;
import com.project.online_book_store.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
}
