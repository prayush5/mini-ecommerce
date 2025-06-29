package com.project.online_book_store.dto;


import com.project.online_book_store.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private int id;

    private String username;

    private String email;
    
    private Role role;

    private List<PurchasedBookDTO> purchasedBook;

    private List<PurchasedMedicineDTO> purchasedMedicine;

    private List<CartItemDTO> cartItem;


}
