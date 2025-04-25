package com.project.online_book_store.mapper;

import com.project.online_book_store.dto.ReviewDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "medicine.name", target = "medicineName")
    ReviewDTO toReviewDTO(Review review);
}
