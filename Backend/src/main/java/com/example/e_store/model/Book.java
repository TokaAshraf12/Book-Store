package com.example.e_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ISBN;
    @Column(nullable = false, length = 150)
    private String title;
    private Double price;
    @Column(nullable = false, length = 20)
    private String category;
    private Integer noOfCopies;
    private Integer threshold;
    @Column(nullable = false, length = 8)
    private String publicationYear;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "authors", referencedColumnName = "author_id")
    private Set<Author> authors;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher", referencedColumnName = "name")
    private Publisher publisher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId", referencedColumnName = "userId")
    private User manager;
    @Lob
    private String description;
    @Lob
    // @Column(name = "productImage", length = 1000)
    private byte[] image;
}
