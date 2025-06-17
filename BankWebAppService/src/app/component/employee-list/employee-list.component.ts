import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Employee } from '../../models/Employee';
import { EmployeeService } from '../../service/employee.service';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

interface FileUploadResponse {
  success: boolean;
  message: string;
  totalRecords: number;
  successfulRecords: number;
  failedRecords: number;
  errors?: string[];
}

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatSnackBarModule]
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  filteredEmployees: Employee[] = [];
  employeeForm: FormGroup;
  isEditing = false;
  currentEmployeeId: number | null = null;
  showForm = false;
  isUploading = false;
  selectedFile: File | null = null;
  
  // Search and Filter properties
  searchTerm: string = '';
  genderFilter: string = '';
  ageRangeFilter: string = '';
  
  // View properties
  viewMode: 'table' | 'card' = 'table';
  showEmployeeDetails = false;
  selectedEmployee: Employee | null = null;

  constructor(
    private employeeService: EmployeeService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {
    this.employeeForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      age: ['', [Validators.required, Validators.min(18), Validators.max(100)]],
      gender: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.getEmployees();
    this.showForm = false;
  }

  getEmployees() {
    this.employeeService.getEmployees().subscribe({
      next: (data) => {
        this.employees = data;
        this.filteredEmployees = [...data];
        this.applyFilters();
      },
      error: (error) => {
        console.error('Error fetching employees:', error);
        this.showNotification('Failed to load employees. Please try again later.', 'error');
      }
    });
  }

  // Search and Filter Methods
  onSearch(): void {
    this.applyFilters();
  }

  applyFilters(): void {
    let filtered = [...this.employees];

    // Apply text search
    if (this.searchTerm) {
      const searchLower = this.searchTerm.toLowerCase();
      filtered = filtered.filter(employee =>
        employee.name.toLowerCase().includes(searchLower) ||
        employee.age.toString().includes(searchLower) ||
        employee.gender.toLowerCase().includes(searchLower)
      );
    }

    // Apply gender filter
    if (this.genderFilter) {
      filtered = filtered.filter(employee => employee.gender === this.genderFilter);
    }

    // Apply age range filter
    if (this.ageRangeFilter) {
      filtered = filtered.filter(employee => {
        const age = Number(employee.age);
        switch (this.ageRangeFilter) {
          case '18-25':
            return age >= 18 && age <= 25;
          case '26-35':
            return age >= 26 && age <= 35;
          case '36-45':
            return age >= 36 && age <= 45;
          case '46-60':
            return age >= 46 && age <= 60;
          case '60+':
            return age > 60;
          default:
            return true;
        }
      });    }

    this.filteredEmployees = filtered;
  }

  clearSearch(): void {
    this.searchTerm = '';
    this.applyFilters();
  }

  clearAllFilters(): void {
    this.searchTerm = '';
    this.genderFilter = '';
    this.ageRangeFilter = '';
    this.applyFilters();
  }

  // View Mode Methods
  setViewMode(mode: 'table' | 'card'): void {
    this.viewMode = mode;
  }

  // Employee Details Methods
  viewEmployee(employee: Employee): void {
    this.selectedEmployee = employee;
    this.showEmployeeDetails = true;
  }

  closeEmployeeDetails(): void {
    this.showEmployeeDetails = false;
    this.selectedEmployee = null;
  }

  editFromDetails(): void {
    if (this.selectedEmployee) {
      this.closeEmployeeDetails();
      this.openEditForm(this.selectedEmployee);
    }
  }

  // Form Methods
  openAddForm() {
    this.isEditing = false;
    this.currentEmployeeId = null;
    this.employeeForm.reset();
    this.showForm = true;
  }

  openEditForm(employee: Employee) {
    this.isEditing = true;
    this.currentEmployeeId = Number(employee.id);
    this.employeeForm.setValue({
      name: employee.name,
      age: employee.age,
      gender: employee.gender
    });
    this.showForm = true;
  }

  cancelForm() {
    this.showForm = false;
    this.employeeForm.reset();
    this.isEditing = false;
    this.currentEmployeeId = null;
  }

  submitForm() {
    if (this.employeeForm.invalid) {
      this.employeeForm.markAllAsTouched();
      this.showNotification('Please fill in all required fields correctly.', 'error');
      return;
    }

    const employeeData: Employee = this.employeeForm.value;

    if (this.isEditing && this.currentEmployeeId) {
      this.employeeService.updateEmployee(this.currentEmployeeId, employeeData).subscribe({
        next: () => {
          this.getEmployees();
          this.cancelForm();
          this.showNotification('Employee updated successfully!', 'success');
        },
        error: (error) => {
          console.error('Error updating employee:', error);
          this.showNotification('Failed to update employee. Please try again.', 'error');
        }
      });
    } else {
      this.employeeService.addEmployee(employeeData).subscribe({
        next: () => {
          this.getEmployees();
          this.cancelForm();
          this.showNotification('Employee added successfully!', 'success');
        },
        error: (error) => {
          console.error('Error adding employee:', error);
          this.showNotification('Failed to add employee. Please try again.', 'error');
        }
      });
    }
  }

  deleteEmployee(id: number) {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: () => {
          this.getEmployees();
          this.showNotification('Employee deleted successfully!', 'success');
        },
        error: (error) => {
          console.error('Error deleting employee:', error);
          this.showNotification('Failed to delete employee. Please try again.', 'error');
        }
      });
    }
  }

  // File Upload Methods
  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      // Validate file type
      const allowedTypes = [
        'text/csv',
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ];
      const fileExtension = file.name.toLowerCase().split('.').pop();
      const validExtensions = ['csv', 'xls', 'xlsx'];

      if (!allowedTypes.includes(file.type) && !validExtensions.includes(fileExtension || '')) {
        this.showNotification('Please select a valid CSV or Excel file.', 'error');
        event.target.value = ''; // Clear the input
        return;
      }

      // Validate file size (max 5MB)
      const maxSize = 5 * 1024 * 1024; // 5MB
      if (file.size > maxSize) {
        this.showNotification('File size must be less than 5MB.', 'error');
        event.target.value = ''; // Clear the input
        return;
      }

      this.selectedFile = file;
      this.showNotification(`File "${file.name}" selected. Click "Upload File" to proceed.`, 'info');
    }
  }

  uploadFile() {
    if (!this.selectedFile) {
      this.showNotification('Please select a file first.', 'error');
      return;
    }

    this.isUploading = true;
    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.http.post<FileUploadResponse>('http://localhost:8080/api/employees/upload', formData).subscribe({
      next: (response) => {
        this.isUploading = false;
        this.selectedFile = null;
        if (response.success) {
          this.showNotification(
            `Upload successful! ${response.successfulRecords} employees added.`,
            'success'
          );
          this.getEmployees(); // Refresh the employee list
        } else {
          this.showNotification(response.message, 'error');
        }
        // Clear the file input
        const fileInput = document.getElementById('fileInput') as HTMLInputElement;
        if (fileInput) {
          fileInput.value = '';
        }
      },
      error: (error) => {
        this.isUploading = false;
        this.selectedFile = null;
        console.error('Error uploading file:', error);
        let errorMessage = 'Failed to upload file. Please try again.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        this.showNotification(errorMessage, 'error');
        // Clear the file input
        const fileInput = document.getElementById('fileInput') as HTMLInputElement;
        if (fileInput) {
          fileInput.value = '';
        }
      }
    });
  }

  // Download Methods
  downloadEmployeesCsv() {
    this.showNotification('Preparing CSV download...', 'info');
    // Create a temporary link element to trigger download
    const link = document.createElement('a');
    link.href = 'http://localhost:8080/api/employees/export/csv';
    link.download = 'employees.csv';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    setTimeout(() => {
      this.showNotification('CSV download started!', 'success');
    }, 500);
  }

  downloadTemplate() {
    this.showNotification('Downloading template...', 'info');
    // Create a temporary link element to trigger download
    const link = document.createElement('a');
    link.href = 'http://localhost:8080/api/employees/template/csv';
    link.download = 'employee_template.csv';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    setTimeout(() => {
      this.showNotification('Template downloaded!', 'success');
    }, 500);
  }

  // Utility Methods
  showNotification(message: string, type: 'success' | 'error' | 'info' = 'info') {
    let panelClass = ['custom-snackbar'];
    switch (type) {
      case 'success':
        panelClass.push('success-snackbar');
        break;
      case 'error':
        panelClass.push('error-snackbar');
        break;
      case 'info':
        panelClass.push('info-snackbar');
        break;
    }

    this.snackBar.open(message, 'Close', {
      duration: type === 'error' ? 5000 : 3000,
      verticalPosition: 'top',
      horizontalPosition: 'right',
      panelClass: panelClass
    });
  }

  // Form validation helpers
  getErrorMessage(fieldName: string): string {
    const field = this.employeeForm.get(fieldName);
    if (field?.hasError('required')) {
      return `${fieldName.charAt(0).toUpperCase() + fieldName.slice(1)} is required`;
    }
    if (fieldName === 'name' && field?.hasError('minlength')) {
      return 'Name must be at least 2 characters long';
    }
    if (fieldName === 'age') {
      if (field?.hasError('min')) {
        return 'Age must be at least 18';
      }
      if (field?.hasError('max')) {
        return 'Age must be less than 100';
      }
    }
    return '';
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.employeeForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  logout(): void {
    this.authService.logout(); // Clear user session/token
    this.router.navigate(['/login']); // Redirect to login page
  }
}
