package com.project.online_book_store.service.serviceImpl;


import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.helper.MyExcelHelper;
import com.project.online_book_store.mapper.PurchasedBookMapper;
import com.project.online_book_store.repository.BookRepository;
import com.project.online_book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PurchasedBookMapper purchasedBookMapper;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> findBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public PurchasedBookDTO getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book==null){
            throw new ResourceNotFoundException("Book not found with title: " + title);
        }
        return purchasedBookMapper.toBookDTO(book);
    }

    @Override
    public List<PurchasedBookDTO> getBookByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        if (books.isEmpty()){
            throw new ResourceNotFoundException("Author not found");
        }
        return books.stream().map(purchasedBookMapper::toBookDTO).toList();
    }

    @Override
    public List<PurchasedBookDTO> getBookByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("Genre not found");
        }
        return books.stream()
                .map(purchasedBookMapper::toBookDTO)
                .toList();
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        try {
            List<Book> books = MyExcelHelper.convertExcelToListOfBook(file.getInputStream());
            this.bookRepository.saveAll(books);
        } catch (Exception e) {
            throw new IOException("Failed to save books: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }


}
