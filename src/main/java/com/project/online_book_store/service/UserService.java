package com.project.online_book_store.service;

import com.project.online_book_store.dto.UserDetailsDTO;
import com.project.online_book_store.dto.UserResponse;
import com.project.online_book_store.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserResponse> findUser(Pageable pageable);
    UserDetailsDTO getUserById(int id);
    boolean updateUser(int id, User updatedUser);
    boolean deleteUserById(int id);
    UserResponse saveUserr(UserDetailsDTO userDetailsDTO);
    UserResponse loginUser(String email, String password);

}
