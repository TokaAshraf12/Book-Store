package com.example.e_store.controller;

import com.example.e_store.dto.BookResponse;
import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.dto.ProductEdit;
import com.example.e_store.dto.ProductRequest;
import com.example.e_store.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final BookService productService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {"multipart/form-data", "application/json"},
            value = "/create"
    )
    public ResponseEntity<?> createProduct(
            @RequestPart("product") ProductRequest productRequest,
            @RequestPart("imageFile") MultipartFile image) {
        productService.save(productRequest, image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/all"
    )
    public ResponseEntity<List<BookSpecificDetails>> getAllProducts() {
        log.info("Getting All Products .. ");
        return ResponseEntity.ok().body(productService.getAllBooks());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/owner/{email}"
    )
    public ResponseEntity<BookResponse> getSpecificProduct(@PathVariable Long id, @PathVariable String email) {
        log.info("Getting Specific Product .. ");
        return ResponseEntity.ok().body(productService.getSpecificProduct(id, email));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {"application/json"},
            value = "/edit"
    )
    public ResponseEntity<?> editProduct(@RequestBody ProductEdit productEdit) {
        productService.editProduct(productEdit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
