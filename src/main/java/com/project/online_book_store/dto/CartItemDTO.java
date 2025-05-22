package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@Setter
public class CartItemDTO {

    private int id;
    private String bookTitle;
    private String medicineName;
    private int quantityDTO;
    private BigDecimal priceDTO;
}
