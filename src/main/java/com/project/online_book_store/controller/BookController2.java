package com.project.online_book_store.controller;

import com.project.online_book_store.entity.Book;
import com.project.online_book_store.helper.MyExcelHelper;
import com.project.online_book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class BookController2 {

    @Autowired
    private BookService bookService;

    @PostMapping("/book/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file");
        }

        if (!MyExcelHelper.checkExcelFormat(file)) {
            return ResponseEntity.badRequest().body("Please upload an Excel file (.xlsx)");
        }

        try {
            bookService.save(file);
            return ResponseEntity.ok(Map.of(
                    "message", "File uploaded successfully",
                    "size", file.getSize()
            ));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/api/book")
    public ResponseEntity<List<Book>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}

