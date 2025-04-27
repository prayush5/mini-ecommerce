package com.project.online_book_store.service;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.entity.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);
    List<Book> findBooks();
    PurchasedBookDTO getBookByTitle(String title);
    List<PurchasedBookDTO> getBookByAuthor(String author);
    List<PurchasedBookDTO> getBookByGenre(String genre);
}
