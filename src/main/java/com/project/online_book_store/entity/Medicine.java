package com.project.online_book_store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String manufacturer;

    private BigDecimal price;

    private int stockQuantity;

    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}


