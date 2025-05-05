package com.project.online_book_store.repository;

import com.project.online_book_store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser_UserId(int userId);
}
