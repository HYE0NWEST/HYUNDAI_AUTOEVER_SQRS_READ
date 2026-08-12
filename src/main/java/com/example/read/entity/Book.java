package com.example.read.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    private Long bid;
    private String title;
    private String author;
    private String category;
    private int pages;
    private int price;
    private String published_date;
    private String description;
}