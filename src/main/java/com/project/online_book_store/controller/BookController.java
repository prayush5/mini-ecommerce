package com.project.online_book_store.controller;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.mapper.PurchasedBookMapper;
import com.project.online_book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PurchasedBookMapper purchasedBookMapper;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/view")
    public ResponseEntity<Page<PurchasedBookDTO>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.findBooks(pageable);

        Page<PurchasedBookDTO> dtoPage = bookPage.map(purchasedBookMapper::toBookDTO);
        return ResponseEntity.ok(dtoPage);
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
