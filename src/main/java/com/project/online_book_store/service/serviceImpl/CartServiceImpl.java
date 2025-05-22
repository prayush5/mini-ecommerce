package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.entity.CartItem;
import com.project.online_book_store.entity.Medicine;
import com.project.online_book_store.entity.User;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.mapper.CartItemMapper;
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
    private CartItemMapper cartItemMapper;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public void addToCart(CartItemDTO cartItemDTO, int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));

        List<CartItem> existingItems = cartItemRepository.findByUser_UserId(userId);

        for (CartItem existing : existingItems) {
            if (cartItemDTO.getBookTitle() != null &&
                    existing.getBook() != null &&
                    cartItemDTO.getBookTitle().equals(existing.getBook().getTitle())) {

                Book book = existing.getBook();
                if (book.getAvailability() < cartItemDTO.getQuantityDTO()) {
                    throw new IllegalArgumentException("Not enough stock available for book: " + book.getTitle());
                }

                existing.setQuantity(existing.getQuantity() + cartItemDTO.getQuantityDTO());
                book.setAvailability(book.getAvailability() - cartItemDTO.getQuantityDTO());
                bookRepository.save(book);
                cartItemRepository.save(existing);
                return;
            }

            if (cartItemDTO.getMedicineName() != null &&
                    existing.getMedicine() != null &&
                    cartItemDTO.getMedicineName().equals(existing.getMedicine().getName())) {

                Medicine medicine = existing.getMedicine();
                if (medicine.getStockQuantity() < cartItemDTO.getQuantityDTO()) {
                    throw new IllegalArgumentException("Not enough stock available for medicine: " + medicine.getName());
                }

                existing.setQuantity(existing.getQuantity() + cartItemDTO.getQuantityDTO());
                medicine.setStockQuantity(medicine.getStockQuantity() - cartItemDTO.getQuantityDTO());
                medicineRepository.save(medicine);
                cartItemRepository.save(existing);
                return;
            }
        }

        CartItem item = new CartItem();
        item.setUser(user);
        item.setQuantity(cartItemDTO.getQuantityDTO());

        if (cartItemDTO.getBookTitle() != null) {
            List<Book> books = bookRepository.findByTitle(cartItemDTO.getBookTitle());

            if (books.isEmpty()) {
                throw new ResourceNotFoundException("Book not found with title: " + cartItemDTO.getBookTitle());
            }

            Book book = books.get(0);

            if (book.getAvailability() < cartItemDTO.getQuantityDTO()) {
                throw new IllegalArgumentException("Not enough stock available for book: " + book.getTitle());
            }

            book.setAvailability(book.getAvailability() - cartItemDTO.getQuantityDTO());
            bookRepository.save(book);

            item.setBook(book);
            item.setPrice(book.getPrice());
        }


        if (cartItemDTO.getMedicineName() != null) {
            Medicine medicine = medicineRepository.findByName(cartItemDTO.getMedicineName());
            if (medicine == null) {
                throw new ResourceNotFoundException("Medicine not found with name: " + cartItemDTO.getMedicineName());
            }

            if (medicine.getStockQuantity() < cartItemDTO.getQuantityDTO()){
                throw new IllegalArgumentException("Not enough stock available for medicine: " + medicine.getName());
            }

            medicine.setStockQuantity(medicine.getStockQuantity() - cartItemDTO.getQuantityDTO());
            medicineRepository.save(medicine);

            item.setMedicine(medicine);
            item.setPrice(medicine.getPrice());
        }

        if (item.getBook() == null && item.getMedicine() == null) {
            throw new IllegalArgumentException("Cart item must contain either book or medicine");
        }

        CartItem savedItem = cartItemRepository.save(item);
        System.out.println("Saved cart item with id: " + savedItem.getId());
    }


    public List<CartItemDTO> getUserCart(int userId) {
        List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
        return cartItems
                .stream()
                .map(cartItemMapper::toCartItemDTO)
                .toList();
    }
}


