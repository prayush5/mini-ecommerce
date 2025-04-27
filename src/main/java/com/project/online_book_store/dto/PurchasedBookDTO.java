package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchasedBookDTO {

    private String bookTitle;

    private String bookAuthor;

    private String bookGenre;

    private int quantity;

    private BigDecimal priceDTO;
}
