package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PurchasedMedicineDTO {

    private String medicineName;

    private int quantity;

    private double price;

    private String manufacturer;

    private LocalDate manufactureDate;

    private LocalDate expiryDate;
}
