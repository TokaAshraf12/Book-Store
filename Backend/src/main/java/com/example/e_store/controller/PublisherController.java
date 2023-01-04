package com.example.e_store.controller;

import com.example.e_store.model.Publisher;
import com.example.e_store.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {"application/json"},
            value = "/create"
    )
    public ResponseEntity<?> createProduct(@RequestBody Publisher publisher) {
        publisherService.addPublisher(publisher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/all"
    )
    public ResponseEntity<List<Publisher>> getAllProducts() {
        log.info("Getting All Products .. ");
        return ResponseEntity.ok().body(publisherService.getAllPublishers());
    }
}
