package com.project.online_book_store.controller;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(bookService.findBooks());
    }

    @GetMapping("/view/{title}")
    public ResponseEntity<PurchasedBookDTO> getByTitle(@PathVariable String title) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/view/author/{author}")
    public ResponseEntity<List<PurchasedBookDTO>> getByAuthor(@PathVariable String author) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookService.getBookByAuthor(author));
    }

    @GetMapping("/view/genre/{genre}")
    public ResponseEntity<List<PurchasedBookDTO>> getByGenre(@PathVariable String genre) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookService.getBookByGenre(genre));
    }

}
