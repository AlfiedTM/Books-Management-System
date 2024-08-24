package com.bits.bs.DTOs;

import com.bits.bs.models.Book;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDto {

    private long id;

    private String title;

    private String author;

    private String genre;

    public Book mapDtoToBook() {
        return Book.builder()
                .title(this.title)
                .author(this.author)
                .genre(this.genre)
                .build();
    }
}
