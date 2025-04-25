package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchasedMedicineDTO {

    private String medicineName;
    private int quantity;
    private BigDecimal price;
}
