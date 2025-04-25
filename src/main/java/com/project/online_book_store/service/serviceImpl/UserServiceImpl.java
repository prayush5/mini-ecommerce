package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.dto.UserDetailsDTO;
import com.project.online_book_store.entity.User;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.mapper.UserMapper;
import com.project.online_book_store.repository.UserRepository;
import com.project.online_book_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetailsDTO saveUser(UserDetailsDTO userDetailsDTO) {
        User user = userMapper.toUserEntity(userDetailsDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public List<UserDetailsDTO> findUser(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDTO getUserById(int id){
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + id));
        return userMapper.toUserDTO(user);
    }

    @Override
    public boolean updateUser(int id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setUsername(updatedUser.getUsername());
            user1.setPassword(updatedUser.getPassword());
            user1.setEmail(updatedUser.getEmail());
            user1.setReview(updatedUser.getReview());
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
