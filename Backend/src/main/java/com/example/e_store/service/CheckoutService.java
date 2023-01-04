package com.example.e_store.service;

import com.example.e_store.dto.CheckoutProductInfo;
import com.example.e_store.dto.CheckoutRequest;
import com.example.e_store.model.Checkout;
import com.example.e_store.model.CompositeKey;
import com.example.e_store.model.Book;
import com.example.e_store.model.User;
import com.example.e_store.repository.CheckoutRepository;
import com.example.e_store.repository.BookRepository;
import com.example.e_store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CheckoutService {
    private final UserRepository userRepository;
    private final BookRepository productRepository;
    private final CheckoutRepository checkoutRepository;

    public void saveOrder(CheckoutRequest checkoutRequest) {
        Optional<User> user = userRepository.findByEmail(checkoutRequest.getCustomer());
        if (!user.isPresent()) return;
        for (CheckoutProductInfo productInfo : checkoutRequest.getProducts()) {
            Book book = productRepository.getById(productInfo.getProductId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String d = formatter.format(date);
            // Setting Checkout Repository
            Checkout checkout = new Checkout();
            checkout.setCompositeKey(new CompositeKey(user.get(), book, d));
            checkout.setQuantity(productInfo.getQuantity());
            checkoutRepository.save(checkout);
            // Setting Product Repository
            book.setNoOfCopies(book.getNoOfCopies() - productInfo.getQuantity());
            productRepository.save(book);
        }
    }
}
