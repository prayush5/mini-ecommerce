package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {

    private String bookTitle;
    private String medicine_name;
    private int quantityDTO;
}
