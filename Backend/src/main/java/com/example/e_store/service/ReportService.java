package com.example.e_store.service;

import com.example.e_store.dto.ReportTop10Books;
import com.example.e_store.dto.ReportTop5Customers;
import com.example.e_store.dto.ReportTotalSales;
import com.example.e_store.querying.Trending;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportService {

    private final Trending trending;
    private final String separator = FileSystems.getDefault().getSeparator();
    private final String path = String.format("src%smain%sresources%sreports/hello", separator, separator, separator);

    public ReportService() {
        this.trending = new Trending();
    }

    public void reportTop5Customers(String extension) throws SQLException, JRException, FileNotFoundException {
        List<ReportTop5Customers> top5Customers = new ArrayList<>();
        ResultSet resultSet = trending.getTop5Customers();
        while (resultSet.next()) {
            ReportTop5Customers customers = new ReportTop5Customers();
            customers.setUserId(resultSet.getLong("user_id"));
            customers.setEmail(resultSet.getString("email"));
            customers.setPhoneNumber(resultSet.getString("phone_number"));
            customers.setTotalPurchase(resultSet.getInt("total_purchase"));
            log.info(customers.getEmail() + " " + customers.getPhoneNumber() + " " + customers.getTotalPurchase());
            top5Customers.add(customers);
        }

        String path = String.format("src%smain%sresources%sreports/report_top_5", separator, separator, separator);
        // load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Top_5_Customers.jrxml");
        log.info("File Reading {} ....", file.getAbsolutePath());
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(top5Customers);
        log.info("File Reading ....");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Book Store");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String finalPath = path + "." + extension;
        log.info("Final Path {}", finalPath);
        if (extension.equalsIgnoreCase("html"))
            JasperExportManager.exportReportToHtmlFile(jasperPrint, finalPath);
        else if (extension.equalsIgnoreCase("pdf"))
            JasperExportManager.exportReportToPdfFile(jasperPrint, finalPath);
    }

    public void reportTop10Selling(String extension) throws FileNotFoundException, JRException, SQLException {
        List<ReportTop10Books> top10Books = new ArrayList<>();
        ResultSet resultSet = trending.getTop10Books();
        while (resultSet.next()) {
            ReportTop10Books book = new ReportTop10Books();
            book.setIsbn(resultSet.getString("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setTotalSoldCopies(resultSet.getInt("total_sold_copies"));
            top10Books.add(book);
        }

        String path = String.format("src%smain%sresources%sreports/report_top_10", separator, separator, separator);
        // load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Top_10_Selling.jrxml");
        log.info("File Reading {} ....", file.getAbsolutePath());
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(top10Books);
        log.info("File Reading ....");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Book Store");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String finalPath = path + "." + extension;
        log.info("Final Path {}", finalPath);
        if (extension.equalsIgnoreCase("html"))
            JasperExportManager.exportReportToHtmlFile(jasperPrint, finalPath);
        else if (extension.equalsIgnoreCase("pdf"))
            JasperExportManager.exportReportToPdfFile(jasperPrint, finalPath);
    }

    public void reportTotalSales(String extension) throws FileNotFoundException, JRException, SQLException {
        List<ReportTotalSales> totalSales = new ArrayList<>();
        ResultSet resultSet = trending.getTopSales();
        while (resultSet.next()) {
            ReportTotalSales book = new ReportTotalSales();
            book.setIsbn(resultSet.getString("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setPrice(resultSet.getDouble("price"));
            book.setTotalSales(resultSet.getInt("total_sales"));
            totalSales.add(book);
        }

        String path = String.format("src%smain%sresources%sreports/report_total_sales", separator, separator, separator);
        // load file and compile it
        File file = ResourceUtils.getFile("classpath:templates/Total_Sales.jrxml");
        log.info("File Reading {} ....", file.getAbsolutePath());
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(totalSales);
        log.info("File Reading ....");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Book Store");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String finalPath = path + "." + extension;
        log.info("Final Path {}", finalPath);
        if (extension.equalsIgnoreCase("html"))
            JasperExportManager.exportReportToHtmlFile(jasperPrint, finalPath);
        else if (extension.equalsIgnoreCase("pdf"))
            JasperExportManager.exportReportToPdfFile(jasperPrint, finalPath);
    }
}
