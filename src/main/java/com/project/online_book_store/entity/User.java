package com.project.online_book_store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Book> book;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> review;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Orders> orders;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ShoppingCart shoppingCart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Medicine> medicine;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderItem> orderItem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CartItem> cartItems;
}
