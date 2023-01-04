package com.example.e_store.repository;

import com.example.e_store.model.Book;
import com.example.e_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByManager(User user);
}
