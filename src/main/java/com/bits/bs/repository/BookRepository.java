package com.bits.bs.repository;

import com.bits.bs.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * Fetch books by author's name
     * @pram author
     * @return List<Books>
     * */
    List<Book> findAllByAuthor(String author);

    /**
     * Fetch books by genre
     * @param genre
     * @return List<Book>
     * */
    List<Book> findAllByGenre(String genre);

    /**
     * Fetch books by title
     * @param title
     * @return List<Book>
     * */
    List<Book> findAllByTitle(String title);

}
