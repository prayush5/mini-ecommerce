package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {

    private String bookTitle;
    private String medicineName;
    private int quantityDTO;
}
