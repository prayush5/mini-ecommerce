package com.project.online_book_store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    private String bookTitle;
    private String medicineName;
    private String comment;
    private int rating;
}
