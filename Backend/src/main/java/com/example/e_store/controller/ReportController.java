package com.example.e_store.controller;

import com.example.e_store.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;

@SpringBootApplication
@RestController
public class ReportController {
    @Autowired
    private ReportService service;

  @GetMapping("/report/{format}")
    public String GenerateReportTop10Selling(@PathVariable String format) throws JRException, FileNotFoundException {
        System.out.println("hellllllloooo");
        return service.exportReportTop10Selling(format);
    }


}
