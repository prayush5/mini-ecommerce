package com.project.online_book_store.repository;

import com.project.online_book_store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
}
