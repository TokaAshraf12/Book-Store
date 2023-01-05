package com.example.e_store.controller;

import com.example.e_store.dto.RequestOrder;
import com.example.e_store.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/all"
    )
    public ResponseEntity<List<RequestOrder>> getAllOrders() {
        log.info("Getting All Orders ... ");
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/confirm/{id}"
    )
    public ResponseEntity<?> getSpecificProduct(@PathVariable Long id) {
        log.info("Confirm Specific Order ... ");
        orderService.confirmOrder(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
