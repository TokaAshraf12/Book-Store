package com.example.e_store.repository;

import com.example.e_store.model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderRequest, Long> {
}
