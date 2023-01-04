package com.example.e_store.controller;

import com.example.e_store.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@SpringBootApplication
@RestController
public class ReportController {
    @Autowired
    private ReportService service;

  @GetMapping("/report10/{format}")
    public String GenerateReportTop10Selling(@PathVariable String format) throws JRException, FileNotFoundException {
        System.out.println("hellllllloooo");
        return service.exportReportTop10Selling(format);
    }

    @GetMapping("/report5/{format}")
    public String GenerateReportTop5Customer(@PathVariable String format) throws JRException, FileNotFoundException {
        System.out.println("hellllllloooo");
        return service.exportReportTop5Customers(format);
    }
    @GetMapping("/reportTotal/{format}")
    public String GenerateReportTotalSales(@PathVariable String format) throws JRException, FileNotFoundException {
        System.out.println("hellllllloooo");
        return service.exportReportTotalSales(format);
    }




}
