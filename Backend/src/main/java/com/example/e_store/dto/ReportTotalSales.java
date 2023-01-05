package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportTotalSales {
    private String isbn;
    private String title;
    private Double price;
    private Integer totalSales;
}
