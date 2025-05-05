package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
    private int userId;
    private Long bookId;
    private Long medicineId;
    private int quantity;
}

