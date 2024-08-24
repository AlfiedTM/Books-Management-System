package com.bits.bs.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String author;


    @Column(nullable = false)
    private String genre;

}
