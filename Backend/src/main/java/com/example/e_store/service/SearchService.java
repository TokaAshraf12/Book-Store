package com.example.e_store.service;

import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.model.Book;
import com.example.e_store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final BookRepository productRepository;

    public List<BookSpecificDetails> getProductsWhenSearchBy(String searchBy) {
        List<BookSpecificDetails> res = new ArrayList<>();
        List<Book> books = productRepository.findAll();
        for (Book book : books) {
            if (contains(book.getTitle(), searchBy) || contains(book.getDescription(), searchBy) ||
                    contains(book.getPrice().toString(), searchBy) || contains(book.getCategory(), searchBy) ||
                    contains(book.getNoOfCopies().toString(), searchBy)) {
                log.info("HOHO: Marry Christmas ... Product #{} is Matching ...", book.getISBN());
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

    private boolean contains(String s1, String s2) {
        return Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();
    }
}
