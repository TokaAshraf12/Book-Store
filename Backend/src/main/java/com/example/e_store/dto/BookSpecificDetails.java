package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSpecificDetails {
    private Long ISBN;
    private String title;
    private String description;
    private Double price;
    private Integer noOfCopies;
    private byte[] image;
}
