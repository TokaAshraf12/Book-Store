package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long ISBN;
    private String title;
    private Double price;
    private String category;
    private Integer noOfCopies;
    private Integer threshold;
    private String publicationYear;
    private Set<String> authors;
    private String publisher;
    private String manager;
    private String description;
    private byte[] image;
    private Boolean isOwner;
}
