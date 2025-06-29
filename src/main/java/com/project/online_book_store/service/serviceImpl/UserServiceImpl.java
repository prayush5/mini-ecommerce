package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.dto.PurchasedMedicineDTO;
import com.project.online_book_store.dto.UserDetailsDTO;
import com.project.online_book_store.dto.UserResponse;
import com.project.online_book_store.entity.Role;
import com.project.online_book_store.entity.User;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.mapper.CartItemMapper;
import com.project.online_book_store.mapper.PurchasedBookMapper;
import com.project.online_book_store.mapper.PurchasedMedicineMapper;
import com.project.online_book_store.mapper.UserMapper;
import com.project.online_book_store.repository.UserRepository;
import com.project.online_book_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PurchasedBookMapper purchasedBookMapper;

    @Autowired
    private PurchasedMedicineMapper purchasedMedicineMapper;

    @Autowired
    private CartItemMapper cartItemMapper;


    @Override
    public UserResponse saveUserr(UserDetailsDTO userDetailsDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDetailsDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + userDetailsDTO.getEmail());
        }

        User user = userMapper.toUserEntity(userDetailsDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roleEnum = userDetailsDTO.getRole();
        user.setRole(roleEnum);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }



    @Override

        public UserResponse loginUser(String email, String password) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
            if (passwordEncoder.matches(password, user.getPassword())) {
                return userMapper.toResponse(user);
            }
            return null;
        }

    @Override
    public Page<UserResponse> findUser(Pageable pageable){
        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    @Override
    public UserDetailsDTO getUserById(int userId){
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        double totalBookAmount = user.getCartItems().stream()
                        .filter(cartItem -> cartItem.getBook()!=null)
                        .map(cartItem -> cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .doubleValue();

        double totalMedicineAmount = user.getCartItems().stream()
                .filter(cartItem -> cartItem.getMedicine() != null)
                .map(cartItem -> cartItem.getMedicine().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();

        dto.setPurchasedBook(user.getCartItems().stream()
                .filter(cartItem -> cartItem.getBook() != null)
                .map(cartItem -> {
                    PurchasedBookDTO purchasedBookDTO = purchasedBookMapper.toBookDTO(cartItem.getBook());
                    purchasedBookDTO.setQuantity(cartItem.getQuantity());
                    return purchasedBookDTO;
                })
                .collect(Collectors.toList()));

        dto.setPurchasedMedicine(user.getCartItems().stream()
                .filter(cartItem -> cartItem.getMedicine() != null)
                .map(cartItem -> {
                    PurchasedMedicineDTO purchasedMedicineDTO = purchasedMedicineMapper.toMedicineDTO(cartItem.getMedicine());
                    purchasedMedicineDTO.setQuantity(cartItem.getQuantity());
                    return purchasedMedicineDTO;
                })
                .collect(Collectors.toList()));

        dto.setCartItem(user.getCartItems().stream()
                .map(cartItemMapper::toCartItemDTO)
                .collect(Collectors.toList()));

        dto.setTotalBookAmount(totalBookAmount);
        dto.setTotalMedicineAmount(totalMedicineAmount);

        return dto;
    }


    @Override
    public boolean updateUser(int id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setUsername(updatedUser.getUsername());
            user1.setPassword(updatedUser.getPassword());
            user1.setEmail(updatedUser.getEmail());
            user1.setBook(updatedUser.getBook());
            user1.setMedicine(updatedUser.getMedicine());
            user1.setCartItems(updatedUser.getCartItems());
            user1.setShoppingCart(updatedUser.getShoppingCart());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
