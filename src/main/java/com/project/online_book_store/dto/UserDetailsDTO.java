package com.project.online_book_store.dto;

import com.project.online_book_store.entity.Orders;
import com.project.online_book_store.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserDetailsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @Email
    private String email;

    private List<ReviewDTO> review;

    private List<PurchasedBookDTO> purchasedBook;

    private List<PurchasedMedicineDTO> purchasedMedicine;

    private List<CartItemDTO> cartItem;


}
