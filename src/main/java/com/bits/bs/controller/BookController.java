package com.bits.bs.controller;

import com.bits.bs.DTOs.ApiResponse;
import com.bits.bs.DTOs.BookDto;
import com.bits.bs.models.Book;
import com.bits.bs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        List<Book> books;
        ApiResponse<List<Book>> response;
        try{
            books = bookService.getAllBooks();
            response = new ApiResponse<>(HttpStatus.OK.value(), "Successful", books, true);
        }catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Create a Book
     * @param bookDto
     * */
    @PostMapping("/books")
    private ResponseEntity<ApiResponse<Book>> addNewBook(@RequestBody BookDto bookDto) {
        ApiResponse<Book> response;
        try{
            Book newBook = bookDto.mapDtoToBook();

            response = new ApiResponse<>(HttpStatus.OK.value(),
                    "Successful",
                    bookService.save(newBook),
                    true);
        }catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*Fetch a particular book by id
    * */
    @GetMapping("/books/{id}")
    private ResponseEntity<ApiResponse<Book>> GetBookById(@PathVariable long id) {
        ApiResponse<Book> response;
        try{
            Book newBook = bookService.getBookById(id);
            response = new ApiResponse<>(HttpStatus.OK.value(),
                    "Successful",
                    newBook,
                    true);
        }catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*Delete a particular book by id
    * */
    @DeleteMapping("/books/{id}")
    private ResponseEntity<ApiResponse<String>> DeleteBookById(@PathVariable long id) {
        ApiResponse<String> response;
        try{
           bookService.deleteBookById(id);
            response = new ApiResponse<>(HttpStatus.OK.value(),
                    "Successful",
                    "Successful",
                    true);
        }catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*Update a particular book
    * */
    @PutMapping("/books")
    private ResponseEntity<ApiResponse<Book>> DeleteBookById(@RequestBody BookDto bookDto) {
        ApiResponse<Book> response;
        try{
            Book newBook = bookDto.mapDtoToBook();
            Book book = bookService.getBookById(newBook.getId());
            if(book != null){
                response = new ApiResponse<>(HttpStatus.OK.value(),
                        "Successful",
                        bookService.save(newBook),
                        true);
            }else {
                response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Invalid book details", null, false);
            }
        }catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
