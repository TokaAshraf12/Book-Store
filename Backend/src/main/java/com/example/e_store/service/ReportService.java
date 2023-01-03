package com.example.e_store.service;

import com.example.e_store.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    public String exportReportTop5Customers(String fileFormat ) throws FileNotFoundException, JRException {
        List<RegisterRequest> employees = new ArrayList<>();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Top_5_Customers.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path="C:\\Users\\origenal\\Downloads\\Report";
        if(fileFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+".html");
        }
        if(fileFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+".pdf");
        }
        return "Report Generated In Path: " + path;
    }

    public String exportReportTop10Selling(String fileFormat) throws FileNotFoundException, JRException {
        List<RegisterRequest> employees = new ArrayList<>();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Top_10_Selling.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Book Store");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        String path="C:\\Users\\origenal\\Downloads\\Report";
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
       String path="C:\\Users\\origenal\\Downloads\\Report";
       if(fileFormat.equalsIgnoreCase("html")){
           JasperExportManager.exportReportToHtmlFile(jasperPrint, path+".html");
       }
       if(fileFormat.equalsIgnoreCase("pdf")){
           JasperExportManager.exportReportToPdfFile(jasperPrint,path+".pdf");
       }
       return "Report Generated In Path: " + path;
   }
}
