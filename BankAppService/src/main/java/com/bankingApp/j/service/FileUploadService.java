
package com.bankingApp.j.service;

import com.bankingApp.j.model.Employee;
import com.bankingApp.j.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> uploadEmployeesFromFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        List<Employee> employees = new ArrayList<>();

        if (fileName != null && fileName.toLowerCase().endsWith(".csv")) {
            employees = parseCSVFile(file);
        } else if (fileName != null && (fileName.toLowerCase().endsWith(".xlsx") || fileName.toLowerCase().endsWith(".xls"))) {
            employees = parseExcelFile(file);
        } else {
            throw new IllegalArgumentException("Unsupported file format. Please upload CSV or Excel files only.");
        }

        // Save all employees to database
        return employeeRepository.saveAll(employees);
    }

    private List<Employee> parseCSVFile(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            
            for (CSVRecord csvRecord : csvParser) {
                try {
                    String name = csvRecord.get("Name");
                    String ageStr = csvRecord.get("Age");
                    String gender = csvRecord.get("Gender");

                    // Validate required fields
                    if (name == null || name.trim().isEmpty()) {
                        System.err.println("Skipping record with empty name: " + csvRecord);
                        continue;
                    }

                    Integer age = null;
                    if (ageStr != null && !ageStr.trim().isEmpty()) {
                        try {
                            age = Integer.parseInt(ageStr.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid age format for record: " + csvRecord + ". Skipping.");
                            continue;
                        }
                    }

                    Employee employee = new Employee(name.trim(), age, gender != null ? gender.trim() : null);
                    employees.add(employee);
                    
                } catch (Exception e) {
                    System.err.println("Error parsing CSV record: " + csvRecord + ". Error: " + e.getMessage());
                }
            }
        }
        
        return employees;
    }

    private List<Employee> parseExcelFile(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        
        try (Workbook workbook = file.getOriginalFilename().toLowerCase().endsWith(".xlsx") 
                ? new XSSFWorkbook(file.getInputStream()) 
                : new HSSFWorkbook(file.getInputStream())) {
            
            Sheet sheet = workbook.getSheetAt(0);
            
            // Skip header row (row 0)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                try {
                    Cell nameCell = row.getCell(0);
                    Cell ageCell = row.getCell(1);
                    Cell genderCell = row.getCell(2);

                    String name = getCellValueAsString(nameCell);
                    if (name == null || name.trim().isEmpty()) {
                        System.err.println("Skipping row " + (i + 1) + " with empty name");
                        continue;
                    }

                    Integer age = null;
                    if (ageCell != null) {
                        String ageStr = getCellValueAsString(ageCell);
                        if (ageStr != null && !ageStr.trim().isEmpty()) {
                            try {
                                age = Integer.parseInt(ageStr.trim());
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid age format in row " + (i + 1) + ". Skipping.");
                                continue;
                            }
                        }
                    }

                    String gender = getCellValueAsString(genderCell);

                    Employee employee = new Employee(name.trim(), age, gender != null ? gender.trim() : null);
                    employees.add(employee);
                    
                } catch (Exception e) {
                    System.err.println("Error parsing Excel row " + (i + 1) + ". Error: " + e.getMessage());
                }
            }
        }
        
        return employees;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Handle numeric values (convert to string, remove decimal if it's a whole number)
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((int) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    public boolean isValidFileType(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && 
               (fileName.toLowerCase().endsWith(".csv") || 
                fileName.toLowerCase().endsWith(".xlsx") || 
                fileName.toLowerCase().endsWith(".xls"));
    }
}
