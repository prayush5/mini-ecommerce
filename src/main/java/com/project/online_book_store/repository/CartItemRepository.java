package com.project.online_book_store.repository;

import com.project.online_book_store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("SELECT c FROM CartItem c LEFT JOIN FETCH c.user LEFT JOIN FETCH c.book LEFT JOIN FETCH c.medicine WHERE c.user.userId = :userId")
    List<CartItem> findByUser_UserId(@Param("userId") int userId);
}

