package com.project.online_book_store.mapper;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "medicine.name", target = "medicineName")
    @Mapping(source = "quantity", target = "quantityDTO")
    @Mapping(source = "price", target = "priceDTO")
    CartItemDTO toCartItemDTO(CartItem cartItem);
}

