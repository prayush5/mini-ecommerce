package com.project.online_book_store.mapper;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchasedBookMapper {

    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "book.availability", target = "quantity")
    @Mapping(source = "book.price", target = "priceDTO")
    @Mapping(source = "book.author", target = "bookAuthor")
    @Mapping(source = "book.genre", target = "bookGenre")
    PurchasedBookDTO toBookDTO(Book book);
}
