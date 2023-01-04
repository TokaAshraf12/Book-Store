package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String title;
    private Double price;
    private String category;
    private Integer noOfCopies;
    private Integer threshold;
    private String publicationYear;
    private List<String> authors;
    private String publisher;
    private String manager;
    private String description;
}
