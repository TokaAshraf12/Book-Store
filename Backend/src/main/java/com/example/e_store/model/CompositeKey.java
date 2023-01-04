package com.example.e_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CompositeKey implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", referencedColumnName = "userId")
    private User customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book book;
    @Column(nullable = false, name = "dateOfPurchase")
    private String dateOfPurchase;
}
