package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.repository.ShoppingCartRepository;
import com.project.online_book_store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
}
