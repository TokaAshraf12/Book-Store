package com.example.e_store.service;

import com.example.e_store.dto.RegisterRequest;
import com.example.e_store.repository.CheckoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final CheckoutRepository checkoutRepository;
    private final String separator = FileSystems.getDefault().getSeparator();
    private final String path = String.format("src%smain%sresources%sreports", separator, separator, separator);

    public void reportTop5Customers(String extension) {
        List<Long> users = checkoutRepository.getTop5CustomersInLast3Months();
        log.info("Hello: {}", users.size());
        for (Long id : users) {
            log.info("User Top {}", id);
        }
        /*
        List<RegisterRequest> employees = new ArrayList<>();
        // load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Top_5_Customers.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, path);
        return "Report Generated In Path: " + path;
        */
    }

    public String reportTop10Selling(String fileFormat) throws FileNotFoundException, JRException {
        List<Object> books= new ArrayList<>();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Top_10_Selling.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(books,false);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Book Store");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if(fileFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+".html");
        }
        if(fileFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+".pdf");
        }
        return "Report Generated In Path: " + path;
    }

    public String exportReportTotalSales(String fileFormat) throws FileNotFoundException, JRException {
        List<RegisterRequest> employees = new ArrayList<>();

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Total_Sales.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Book Store");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if(fileFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+".html");
        }
        if(fileFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+".pdf");
        }
        return "Report Generated In Path: " + path;
    }
}
