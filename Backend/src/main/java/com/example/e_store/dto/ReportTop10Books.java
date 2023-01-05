package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportTop10Books {
    private String isbn;
    private String title;
    private Integer totalSoldCopies;
}
