package com.example.e_store.service;

import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.model.Book;
import com.example.e_store.repository.BookRepository;
import com.example.e_store.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SortService {

    private final BookRepository productRepository;
    private final SearchRepository searchRepository;

    public List<BookSpecificDetails> getProductsSortedByPrice() {
        List<BookSpecificDetails> res = new ArrayList<>();
        List<Book> books = searchRepository.sortByPrice();

        for (Book book : books) {
            log.info("HOHO: Marry Christmas ... Product #{} is Matching for Sorting By Price ...", book.getISBN());
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
         * Revolution .. He He
        List<Book> books = productRepository.findAll();
        books.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));

        for (Book book : books) {
            log.info("HOHO: Marry Christmas ... Product #{} is Matching for Sorting By Price ...", book.getISBN());
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
        */
        return res;
    }

    public List<BookSpecificDetails> getProductsSortedByQuantityInStock() {
        List<BookSpecificDetails> res = new ArrayList<>();
        List<Book> books = searchRepository.sortByNoOfCopies();

        for (Book book : books) {
            log.info("HOHO: Marry Christmas ... Product #{} is Matching for Sorting By Price ...", book.getISBN());
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
         * // Revolution
        List<BookSpecificDetails> res = new ArrayList<>();
        List<Book> books = productRepository.findAll();
        books.sort(Comparator.comparingInt(Book::getNoOfCopies));

        for (Book book : books) {
            log.info("HOHO: Marry Christmas ... Product #{} is Matching for Sorting By Price ...", book.getISBN());
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
        */
        return res;
    }
}
