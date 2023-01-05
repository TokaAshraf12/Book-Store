package com.example.e_store.repository;

import com.example.e_store.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book b " +
            "WHERE b.title LIKE %:word% OR b.description LIKE %:word% " +
            " OR b.price LIKE %:word% OR b.category LIKE %:word% " +
            "OR b.no_of_copies LIKE %:word% ", nativeQuery = true)
    List<Book> searchByWord(@Param("word") String word);

    @Query(value = "SELECT * FROM book b " +
            "WHERE b.category LIKE :category ",
            nativeQuery = true)
    List<Book> searchByCategory(@Param("category") String category);
}
