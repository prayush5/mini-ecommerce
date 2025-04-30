package com.project.online_book_store.service;

import com.project.online_book_store.dto.UserDetailsDTO;
import com.project.online_book_store.dto.UserResponse;
import com.project.online_book_store.entity.User;

import java.util.List;

public interface UserService {

    List<UserDetailsDTO> findUser();
    UserDetailsDTO getUserById(int id);
    boolean updateUser(int id, User updatedUser);
    boolean deleteUserById(int id);
    UserResponse saveUserr(UserDetailsDTO userDetailsDTO);

}
