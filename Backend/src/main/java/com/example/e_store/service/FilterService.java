package com.example.e_store.service;

import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.model.Book;
import com.example.e_store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilterService {

    private final BookRepository productRepository;

    public List<BookSpecificDetails> getProductsByCategory(String category) {
        List<BookSpecificDetails> res = new ArrayList<>();
        List<Book> books = productRepository.findAll();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                log.info("HOHO: Marry Christmas ... Product #{} is Matching for Category Filtering ...", book.getISBN());
                BookSpecificDetails productSpecificDetails = BookSpecificDetails.builder().
                        bookId(book.getBookId()).
                        ISBN(book.getISBN()).
                        title(book.getTitle()).
                        description(book.getDescription()).
                        price(book.getPrice()).
                        image(book.getImage()).
                        noOfCopies(book.getNoOfCopies()).
                        build();
                res.add(productSpecificDetails);
            }
        }
        return res;
    }
}
