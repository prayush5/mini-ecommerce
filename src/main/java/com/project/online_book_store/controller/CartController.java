package com.project.online_book_store.controller;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.entity.CartItem;
import com.project.online_book_store.mapper.CartItemMapper;
import com.project.online_book_store.repository.CartItemRepository;
import com.project.online_book_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCart(@RequestBody CartItemDTO cartItemDTO, @RequestParam int userId) {
        System.out.println("Received DTO: medicineName=" + cartItemDTO.getMedicineName() +
                ", bookTitle=" + cartItemDTO.getBookTitle() +
                ", quantity=" + cartItemDTO.getQuantityDTO());
        cartService.addToCart(cartItemDTO, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item added to cart");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public List<CartItemDTO> getCartItems(@PathVariable int userId) {
        System.out.println("Fetching cart items for user ID: " + userId);
        List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
        System.out.println("Found " + cartItems.size() + " cart items");
        cartItems.forEach(item -> {
            System.out.println("CartItem: id=" + item.getId() +
                    ", book=" + (item.getBook() != null ? item.getBook().getTitle() : "null") +
                    ", medicine=" + (item.getMedicine() != null ? item.getMedicine().getName() : "null") +
                    ", quantity=" + item.getQuantity() +
                    ", userId=" + (item.getUser() != null ? item.getUser().getUserId() : "null"));
        });
        List<CartItemDTO> dtos = cartItems.stream()
                .map(cartItemMapper::toCartItemDTO)
                .collect(Collectors.toList());
        System.out.println("Mapped DTOs: " + dtos);
        return dtos;
    }

    @DeleteMapping(value = "/remove/{cartItemId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> removeFromCart(@PathVariable int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable int userId){
        cartService.clearCartByUserId(userId);
        return ResponseEntity.ok("Cart cleared!");
    }
}



