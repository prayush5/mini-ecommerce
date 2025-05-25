package com.project.online_book_store.service;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {

    Book saveBook(Book book);
    Page<Book> findBooks(Pageable pageable);
    PurchasedBookDTO getBookByTitle(String title);
    List<PurchasedBookDTO> getBookByAuthor(String author);
    List<PurchasedBookDTO> getBookByGenre(String genre);
    void save(MultipartFile file) throws IOException;
    List<Book> getAllBooks();
    void deleteBookById(Long id);
}
