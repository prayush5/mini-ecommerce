package com.project.online_book_store.mapper;

import com.project.online_book_store.dto.UserDetailsDTO;
import com.project.online_book_store.dto.UserResponse;
import com.project.online_book_store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ReviewMapper.class,
        PurchasedBookMapper.class,
        PurchasedMedicineMapper.class,
        CartItemMapper.class
})
public interface UserMapper {

    @Mapping(source = "userId", target = "id")
    @Mapping(target = "purchasedBook", source = "book")
    @Mapping(target = "purchasedMedicine", source = "medicine")
    @Mapping(target = "cartItem", source = "cartItems")
    UserDetailsDTO toUserDTO(User user);

    @Mapping(target = "userId", source = "id")
    @Mapping(source = "purchasedBook", target = "book")
    @Mapping(source = "purchasedMedicine", target = "medicine")
    @Mapping(source = "cartItem", target = "cartItems")
    @Mapping(source = "password", target = "password")
    User toUserEntity(UserDetailsDTO userDetailsDTO);

    @Mapping(source = "userId", target = "id")
    UserResponse toDTO(User user);

}
