package com.example.e_store.service;

import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.model.Book;
import com.example.e_store.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public List<BookSpecificDetails> getProductsWhenSearchBy(String word) {
        List<BookSpecificDetails> res = new ArrayList<>();
        List<Book> books = searchRepository.searchByWord(word);
        for (Book book : books) {
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

        /*
         * Better Way :)
        List<Book> books = productRepository.findAll();
        for (Book book : books) {
            if (contains(book.getTitle(), word) || contains(book.getDescription(), word) ||
                    contains(book.getPrice().toString(), word) || contains(book.getCategory(), word) ||
                    contains(book.getNoOfCopies().toString(), word)) {
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
        */
        return res;
    }

    /*
     * Comment it
    private boolean contains(String s1, String s2) {
        return Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();
    }
    */
}
