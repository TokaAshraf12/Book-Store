package com.example.e_store.repository;

import com.example.e_store.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
    Optional<Publisher> findByName(String name);
}
