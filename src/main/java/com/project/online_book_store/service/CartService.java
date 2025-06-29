package com.project.online_book_store.service;

import com.project.online_book_store.dto.CartItemDTO;

public interface CartService {
    void addToCart(CartItemDTO cartItemDTO, int userId);

    void clearCartByUserId(int userId);

}
