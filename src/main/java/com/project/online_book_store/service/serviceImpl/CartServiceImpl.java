package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.entity.CartItem;
import com.project.online_book_store.entity.Medicine;
import com.project.online_book_store.entity.User;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.repository.*;
import com.project.online_book_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public void addToCart(CartItemDTO cartItemDTO, int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));

        CartItem item = new CartItem();
        item.setUser(user);
        item.setQuantity(cartItemDTO.getQuantityDTO());

        if (cartItemDTO.getBookTitle() != null) {
            Book book = bookRepository.findByTitle(cartItemDTO.getBookTitle());
            item.setBook(book);
        }

        if (cartItemDTO.getMedicineName() != null) {
            Medicine medicine = medicineRepository.findByName(cartItemDTO.getMedicineName());
            item.setMedicine(medicine);
        }
        cartItemRepository.save(item);
    }

    public List<CartItemDTO> getUserCart(int userId) {
        List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
        return cartItems
                .stream()
                .map(cartItem -> {
                    CartItemDTO dto = new CartItemDTO();
                    dto.setBookTitle(cartItem.getBook() != null ? cartItem.getBook().getTitle(): null);
                    dto.setMedicineName(cartItem.getMedicine() != null ? cartItem.getMedicine().getName(): null);
                    dto.setQuantityDTO(cartItem.getQuantity());
                    return dto;
                })
                .toList();
    }
}


