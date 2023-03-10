package com.example.e_store.service;

import com.example.e_store.dto.BookEdit;
import com.example.e_store.dto.BookRequest;
import com.example.e_store.dto.BookResponse;
import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.model.Author;
import com.example.e_store.model.Book;
import com.example.e_store.model.Publisher;
import com.example.e_store.model.User;
import com.example.e_store.repository.AuthorRepository;
import com.example.e_store.repository.BookRepository;
import com.example.e_store.repository.PublisherRepository;
import com.example.e_store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;

    public void save(BookRequest bookRequest, MultipartFile image) {
        Optional<User> manager = userRepository.findByEmail(bookRequest.getManager());
        if (!manager.isPresent()) return;

        try {
            Book book = new Book();
            book.setISBN(bookRequest.getISBN());
            book.setTitle(bookRequest.getTitle());
            book.setPrice(bookRequest.getPrice());
            book.setCategory(bookRequest.getCategory());
            book.setManager(manager.get());
            book.setNoOfCopies(bookRequest.getNoOfCopies());
            book.setThreshold(bookRequest.getThreshold());
            book.setPublicationYear(bookRequest.getPublicationYear());
            book.setDescription(bookRequest.getDescription());
            book.setImage(image.getBytes());
            // Publisher
            Optional<Publisher> publisher = publisherRepository.findByName(bookRequest.getPublisher());
            if (!publisher.isPresent()) return;
            book.setPublisher(publisher.get());
            // Authors
            for (String author : bookRequest.getAuthors()) {
                Optional<Author> au = authorRepository.findByName(author);
                if (!au.isPresent()) {
                    Author temp = new Author();
                    temp.setName(author);
                    authorRepository.save(temp);
                    book.getAuthors().add(temp);
                    continue;
                }
                book.getAuthors().add(au.get());
                log.info("Author In DB Already: {}", author);
            }

            bookRepository.save(book);
            log.info("Manager {} Added Product #{} To The DB", manager.get().getEmail(), bookRequest.getCategory());
        } catch (IOException e) {
            log.error("Error when adding product: {}", e.getMessage());
        }
    }

    public List<BookSpecificDetails> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookSpecificDetails> res = new ArrayList<>();
        for (Book book : books) {
            res.add(BookSpecificDetails.builder().
                    bookId(book.getBookId()).
                    ISBN(book.getISBN()).
                    title(book.getTitle()).
                    description(book.getDescription()).
                    price(book.getPrice()).
                    image(book.getImage()).
                    noOfCopies(book.getNoOfCopies()).
                    build());
            // log.info("Book ISBN => {}", res.get(res.size() - 1).getISBN());
        }
        return res;
    }

    public BookResponse getSpecificProduct(Long id, String email) {
        Book book = bookRepository.getById(id);
        Set<String> authors = new HashSet<>();
        for (Author author : book.getAuthors()) authors.add(author.getName());
        return BookResponse.builder().
                bookId(book.getBookId()).
                ISBN(book.getISBN()).
                title(book.getTitle()).
                description(book.getDescription()).
                price(book.getPrice()).
                noOfCopies(book.getNoOfCopies()).
                threshold(book.getThreshold()).
                category(book.getCategory()).
                image(book.getImage()).
                publicationYear(book.getPublicationYear()).
                authors(authors).
                publisher(book.getPublisher().getName()).
                manager(book.getManager().getEmail()).
                isOwner(email.equalsIgnoreCase(book.getManager().getEmail())).
                build();
    }

    public void editProduct(BookEdit productEdit) {
        Book book = bookRepository.getById(productEdit.getBookId());
        log.info("Hello Here");
        // Authors
        Set<Author> authorList = new HashSet<>();
        for (String author : productEdit.getAuthors()) {
            Optional<Author> au = authorRepository.findByName(author);
            if (!au.isPresent()) {
                Author temp = new Author();
                temp.setName(author);
                authorRepository.save(temp);
                authorList.add(temp);
                continue;
            }
            authorList.add(au.get());
            log.info("Author: {}", author);
        }
        book.setAuthors(authorList);
        // Publisher
        Optional<Publisher> publisher = publisherRepository.findByName(productEdit.getPublisher());
        publisher.ifPresent(book::setPublisher);
        // the rest
        book.setISBN(productEdit.getISBN());
        book.setNoOfCopies(productEdit.getNoOfCopies());
        book.setCategory(productEdit.getCategory());
        book.setPrice(productEdit.getPrice());
        book.setTitle(productEdit.getTitle());
        book.setDescription(productEdit.getDescription());
        bookRepository.save(book);
    }
}
