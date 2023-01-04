package com.example.e_store.service;

import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.dto.ProfileInfoResponse;
import com.example.e_store.model.Book;
import com.example.e_store.model.User;
import com.example.e_store.repository.CheckoutRepository;
import com.example.e_store.repository.BookRepository;
import com.example.e_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;
    private final BookRepository productRepository;
    private final CheckoutRepository checkoutRepository;

    private User getCurrentUser(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User Email not Found - " + email));
    }

    public ProfileInfoResponse getUserInfo(String email) {
        User user = getCurrentUser(email);
        return ProfileInfoResponse.builder().
                firstName(user.getFirstName()).
                lastName(user.getLastName()).
                email(user.getEmail()).
                phoneNumber(user.getPhoneNumber()).
                dateOfBirth(user.getDateOfBirth()).
                gender(user.getGender()).
                build();
    }

    public List<BookSpecificDetails> getUserOwnerProducts(String email) {
        Optional<User> owner = userRepository.findByEmail(email);
        if (!owner.isPresent()) return new ArrayList<>();
        List<Book> books = productRepository.findAllByManager(owner.get());
        List<BookSpecificDetails> productResponse = new ArrayList<>();
        for (Book book : books) {
            BookSpecificDetails response = BookSpecificDetails.builder().
                    ISBN(book.getISBN()).
                    title(book.getTitle()).
                    price(book.getPrice()).
                    noOfCopies(book.getNoOfCopies()).
                    description(book.getDescription()).
                    image(book.getImage()).
                    build();
            productResponse.add(response);
        }
        return productResponse;
    }

    public List<BookSpecificDetails> getUserPurchasedProducts(String email) {
        Optional<User> owner = userRepository.findByEmail(email);
        if (!owner.isPresent()) return new ArrayList<>();
        List<Long> products = checkoutRepository.findCustomerPurchases(owner.get().getUserId());
        log.info("Length of Purchased Products {}", products.size());
        List<BookSpecificDetails> productResponse = new ArrayList<>();
        for (Long productId : products) {
            Book book = productRepository.getById(productId);
            BookSpecificDetails response = BookSpecificDetails.builder().
                    ISBN(book.getISBN()).
                    title(book.getTitle()).
                    price(book.getPrice()).
                    noOfCopies(book.getNoOfCopies()).
                    description(book.getDescription()).
                    image(book.getImage()).
                    build();
            productResponse.add(response);
        }
        return productResponse;
    }
}
