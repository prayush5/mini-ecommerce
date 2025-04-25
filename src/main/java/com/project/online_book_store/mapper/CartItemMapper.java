package com.project.online_book_store.mapper;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "book_title", target = "bookTitle")
    @Mapping(source = "medicineName", target = "medicine_name")
    @Mapping(source = "quantity" , target = "quantityDTO")
    CartItemDTO toCartItemDTO(CartItem cartItem);
}
