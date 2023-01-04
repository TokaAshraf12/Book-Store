package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEdit {
    private Long bookId;
    private String ISBN;
    private String title;
    private Double price;
    private String category;
    private Integer noOfCopies;
    private String description;
    private List<String> authors;
    private String publisher;
    private String publicationYear;
}
