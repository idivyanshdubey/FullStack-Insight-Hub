import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService } from '../../service/auth.service';
import { LoginRequest, User } from '../../models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatSnackBarModule]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  isLoading = false;
  hidePassword = true;
  returnUrl: string = '/employees';
  focusedField: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rememberMe: [false]
    });
  }

  ngOnInit(): void {
    // Check if user is already authenticated
    if (this.authService.isAuthenticated) {
      this.router.navigate([this.returnUrl]);
      return;
    }

    // Get return URL from route parameters or default to '/employees'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/employees';
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      this.showNotification('Please fill in all required fields correctly.', 'error');
      return;
    }

    this.isLoading = true;
    const credentials: LoginRequest = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    };

    this.authService.login(credentials).subscribe({
      next: (user: User) => {
        this.isLoading = false;
        if (user && user.username) {
          this.authService.setUser(user);
          
          // Handle remember me functionality
          if (this.loginForm.value.rememberMe) {
            localStorage.setItem('rememberMe', 'true');
          }
          
          this.showNotification(`Welcome back, ${user.username}!`, 'success');
          
          // Navigate after a short delay for better UX
          setTimeout(() => {
            this.router.navigate([this.returnUrl]);
          }, 1000);
        } else {
          this.showNotification('Login failed: Invalid response from server', 'error');
        }
      },
      error: (error) => {
        this.isLoading = false;
        console.error('Login error:', error);
        const errorMessage = typeof error.error === 'string' 
          ? error.error 
          : 'Login failed. Please check your credentials and try again.';
        this.showNotification(errorMessage, 'error');
      }
    });
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  navigateToSignup(): void {
    this.router.navigate(['/signup'], { 
      queryParams: { returnUrl: this.returnUrl } 
    });
  }

  onFieldFocus(fieldName: string): void {
    this.focusedField = fieldName;
  }

  onFieldBlur(): void {
    this.focusedField = null;
  }

  onForgotPassword(event: Event): void {
    event.preventDefault();
    // Navigate to forgot password page or show modal
    this.showNotification('Forgot password functionality will be implemented soon.', 'info');
  }

  loginWithGoogle(): void {
    this.isLoading = true;
    // Placeholder for Google OAuth login
    setTimeout(() => {
      this.isLoading = false;
      this.showNotification('Google login will be implemented soon.', 'info');
    }, 2000);
  }

  loginWithGithub(): void {
    this.isLoading = true;
    // Placeholder for GitHub OAuth login
    setTimeout(() => {
      this.isLoading = false;
      this.showNotification('GitHub login will be implemented soon.', 'info');
    }, 2000);
  }

  getErrorMessage(fieldName: string): string {
    const field = this.loginForm.get(fieldName);
    
    if (field?.hasError('required')) {
      return `${this.getFieldDisplayName(fieldName)} is required`;
    }
    
    if (field?.hasError('minlength')) {
      const minLength = field.errors?.['minlength']?.requiredLength;
      return `${this.getFieldDisplayName(fieldName)} must be at least ${minLength} characters`;
    }
    
    if (field?.hasError('email')) {
      return 'Please enter a valid email address';
    }
    
    return '';
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.loginForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  private getFieldDisplayName(fieldName: string): string {
    const fieldNames: { [key: string]: string } = {
      'username': 'Username',
      'password': 'Password',
      'email': 'Email'
    };
    return fieldNames[fieldName] || fieldName.charAt(0).toUpperCase() + fieldName.slice(1);
  }

  private showNotification(message: string, type: 'success' | 'error' | 'info' = 'info'): void {
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

  // Utility method to check if form field has value
  hasValue(fieldName: string): boolean {
    const field = this.loginForm.get(fieldName);
    return !!(field && field.value && field.value.trim().length > 0);
  }

  // Method to handle Enter key press
  onEnterKey(event: KeyboardEvent): void {
    if (event.key === 'Enter' && !this.loginForm.invalid && !this.isLoading) {
      this.onSubmit();
    }
  }

  // Method to clear form
  clearForm(): void {
    this.loginForm.reset();
    this.focusedField = null;
  }
}
