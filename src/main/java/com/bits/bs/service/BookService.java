package com.bits.bs.service;

import com.bits.bs.models.Book;

import java.util.List;

public interface BookService {
    /**
     * Find all Books by author
     * @param author
     * @return List<Book>
     * */
    List<Book> getBooksByAuthor(String author);

    /**
     * Get all Books by title
     * @param title
     * @return List<Book>
     * */
    List<Book> getBooksByTitle(String title);

    /**
     * Get all Books
     * @return List<Book>
     * */
    List<Book> getAllBooks();

    /**
     * Get all Books by id
     * @param id
     * @return List<Book>
     * */
    Book getBookById(long id);


    /**
     * Delete Books by id
     * @param id
     * */
    boolean deleteBookById(long id);


    /**
     * save or update book
     * @param book
     * */
    Book save(Book book);
}
