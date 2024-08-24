package com.bits.bs.controller;

import com.bits.bs.DTOs.ApiResponse;
import com.bits.bs.DTOs.BookDto;
import com.bits.bs.models.Book;
import com.bits.bs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * Fetch all books
     * */
    @GetMapping("/books")
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        ApiResponse<List<Book>> response;
        try{
            List<Book> books = bookService.getAllBooks();
            response = new ApiResponse<>(HttpStatus.OK.value(), "Successful", books, true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Create a Book
     * @param bookDto
     * */
    @PostMapping("/books")
    public ResponseEntity<ApiResponse<Book>> addNewBook(@RequestBody BookDto bookDto) {
        ApiResponse<Book> response;
        try {
            // Map DTO to Book entity
            Book newBook = bookDto.mapDtoToBook();

            // Save the new book using the service
            Book savedBook = bookService.save(newBook);

            response = new ApiResponse<>(HttpStatus.CREATED.value(), "Book created successfully", savedBook, true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            // Handle general exceptions
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred: " + e.getMessage(), null, false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*Fetch a particular book by id
    * */
    @GetMapping("/books/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable long id) {
        ApiResponse<Book> response;
        try {
            // Attempt to retrieve the book by ID
            Book book = bookService.getBookById(id);

            if (book == null) {
                // If no book is found, return 404 Not Found
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Book not found", null, false);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // If book is found, return 200 OK
            response = new ApiResponse<>(HttpStatus.OK.value(), "Successful", book, true);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            // Handle unexpected errors
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred: " + e.getMessage(), null, false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*Delete a particular book by id
    * */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBookById(@PathVariable long id) {
        ApiResponse<String> response;
        try {
            boolean isDeleted = bookService.deleteBookById(id);

            if (!isDeleted) {
                // If no book is found to delete, return 404 Not Found
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Book not found", null, false);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // If the book is successfully deleted, return 204 No Content
            response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Book successfully deleted", null, true);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            // Handle unexpected errors
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred: " + e.getMessage(), null, false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*Update a particular book
    * */
    @PutMapping("/books")
    public ResponseEntity<ApiResponse<Book>> updateBook(@RequestBody Book book) {
        ApiResponse<Book> response;
        try {

            // Check if the book exists
            Book existingBook = bookService.getBookById(book.getId());

            if (existingBook != null) {
                // Update the book
                Book savedBook = bookService.save(book);
                response = new ApiResponse<>(HttpStatus.OK.value(), "Book updated successfully", savedBook, true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Book not found
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Book not found", null, false);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            // Handle unexpected errors
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred: " + e.getMessage(), null, false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
