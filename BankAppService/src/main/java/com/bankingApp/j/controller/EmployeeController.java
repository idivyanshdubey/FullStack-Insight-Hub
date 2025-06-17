package com.bankingApp.j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bankingApp.j.dto.FileUploadResponse;
import com.bankingApp.j.model.Employee;
import com.bankingApp.j.service.EmployeeService;
import com.bankingApp.j.service.FileUploadService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    
    @Autowired
    private EmployeeService service;
    @Autowired
    private FileUploadService fileUploadService;
    
    @GetMapping
    @Operation(summary = "Get All Employees", description = "Fetch a list of all employees.")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get Employee By ID", description = "Fetch an employee's details by providing their ID.")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = service.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @Operation(summary = "Add New Employee", description = "Create a new employee record.")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        System.out.println("POST request received to create employee: " + employee);
        try {
            // For new employees, we should ignore any ID provided by the client
            // and let the database generate a new ID
            Employee newEmployee = new Employee(
                employee.getName(),
                employee.getAge(),
                employee.getGender()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createEmployee(newEmployee));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update Employee", description = "Update an existing employee's details by ID.")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = service.updateEmployee(id, employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Employee", description = "Delete an employee record by providing their ID.")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/stats")
    @Operation(summary = "Get Employee Statistics", description = "Get statistics about employees.")
    public ResponseEntity<Map<String, Object>> getEmployeeStats() {
        try {
            Map<String, Object> stats = service.getEmployeeStats();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            System.err.println("Error getting employee statistics: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

   @GetMapping("/export/csv")
    @Operation(summary = "Export Employees to CSV", description = "Export all employees to a CSV file.")
    public ResponseEntity<Resource> exportEmployeesToCsv() {
        try {
            String filename = "employees.csv";
            ByteArrayResource resource = service.exportEmployeesToCsv();
            
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
        } catch (Exception e) {
            System.err.println("Error exporting employees to CSV: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
     @PostMapping("/upload")
    @Operation(summary = "Upload Employee File", description = "Upload a CSV or Excel file containing employee data.")
    public ResponseEntity<FileUploadResponse> uploadEmployeeFile(@RequestParam("file") MultipartFile file) {
        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(
                    new FileUploadResponse(false, "Please select a file to upload", 0, 0, 0)
                );
            }

            if (!fileUploadService.isValidFileType(file)) {
                return ResponseEntity.badRequest().body(
                    new FileUploadResponse(false, "Invalid file type. Please upload CSV or Excel files only.", 0, 0, 0)
                );
            }

            // Process file
            List<Employee> uploadedEmployees = fileUploadService.uploadEmployeesFromFile(file);
            
            FileUploadResponse response = new FileUploadResponse(
                true, 
                "File uploaded successfully", 
                uploadedEmployees.size(), 
                uploadedEmployees.size(), 
                0
            );
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.err.println("Error uploading file: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new FileUploadResponse(false, "Error processing file: " + e.getMessage(), 0, 0, 0)
            );
        }
    }
    
}



