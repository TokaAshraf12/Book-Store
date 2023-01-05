package com.example.e_store.controller;

import com.example.e_store.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/top-10-selling/{format}"
    )
    public ResponseEntity<?> reportTop10Selling(@PathVariable String format) throws JRException, SQLException, FileNotFoundException {
        log.info("Report Of Top 10 Books ...");
        reportService.reportTop10Selling(format);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/top-5-customers/{format}"
    )
    public ResponseEntity<?> reportTop5Customer(@PathVariable String format) throws SQLException, JRException, FileNotFoundException {
        log.info("Report Of Top 5 Customers ...");
        reportService.reportTop5Customers(format);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/top-sells/{format}"
    )
    public ResponseEntity<?> reportTotalSales(@PathVariable String format) throws JRException, SQLException, FileNotFoundException {
        log.info("Report Of Total Sales ...");
        reportService.reportTotalSales(format);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
