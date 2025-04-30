package com.project.online_book_store.controller;

import com.project.online_book_store.dto.UserDetailsDTO;
import com.project.online_book_store.dto.UserResponse;
import com.project.online_book_store.entity.User;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.mapper.UserMapper;
import com.project.online_book_store.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/get")
    public ResponseEntity<List<UserDetailsDTO>> getUsers() {
        return ResponseEntity.ok(userService.findUser());
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserDetailsDTO userDetailsDTO){
        UserResponse savedUser = userService.saveUserr(userDetailsDTO);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDetailsDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/get/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @PathVariable User updatedUser){
        boolean updated = userService.updateUser(id, updatedUser);
        if (updated){
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        boolean deleted = userService.deleteUserById(id);
        if (deleted){
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("online-book-store/welcome")
    public ResponseEntity<String> welcomeMessage(){
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }
}
