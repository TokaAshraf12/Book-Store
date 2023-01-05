package com.example.e_store.service;

import com.example.e_store.dto.RequestOrder;
import com.example.e_store.model.Book;
import com.example.e_store.model.OrderRequest;
import com.example.e_store.repository.BookRepository;
import com.example.e_store.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public List<RequestOrder> getAllOrders() {
        List<RequestOrder> res = new ArrayList<>();
        List<OrderRequest> orderRequests = orderRepository.findAll();
        for (OrderRequest order : orderRequests) {
            res.add(new RequestOrder(order.getId(), order.getBookId().getISBN(), order.getNoOfCopies()));
        }
        return res;
    }

    public void confirmOrder(Long id) {
        OrderRequest orderRequest = orderRepository.getById(id);

        // Done Through Trigger :)
        // Book book = bookRepository.getById(orderRequest.getBookId().getBookId());
        // book.setNoOfCopies(book.getNoOfCopies() + orderRequest.getNoOfCopies());
        // bookRepository.save(book);

        // Delete This Order
        orderRepository.delete(orderRequest);
    }
}
