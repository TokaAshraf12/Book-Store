package com.example.e_store.repository;

import com.example.e_store.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
    Optional<Publisher> findByName(String name);
}
