package com.bits.bs.manager;

import com.bits.bs.models.Book;
import com.bits.bs.repository.BookRepository;
import com.bits.bs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteBookById(long id) {
        // Check if the book exists
        if (bookRepository.existsById(id)) {
            // Delete the book
            bookRepository.deleteById(id);
            return true; // Indicate that the deletion was successful
        }
        return false;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
