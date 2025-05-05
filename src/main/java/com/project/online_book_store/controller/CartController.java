package com.project.online_book_store.controller;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.entity.CartItem;
import com.project.online_book_store.repository.CartItemRepository;
import com.project.online_book_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItemDTO cartItemDTO, @RequestParam int userId){
        System.out.println("Received DTO: medicineName=" + cartItemDTO.getMedicineName() +
                ", bookTitle=" + cartItemDTO.getBookTitle() +
                ", quantity=" + cartItemDTO.getQuantityDTO());
        cartService.addToCart(cartItemDTO, userId);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/user/{userId}")
    public List<CartItemDTO> getCartItems(@PathVariable int userId) {
        List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
        return cartItems.stream()
                .map(cartItem -> {
                    CartItemDTO dto = new CartItemDTO();
                    dto.setBookTitle(cartItem.getBook() != null ? cartItem.getBook().getTitle() : null);
                    dto.setMedicineName(cartItem.getMedicine() != null ? cartItem.getMedicine().getName() : null);
                    dto.setQuantityDTO(cartItem.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
