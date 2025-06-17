import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService } from '../../service/auth.service';
import { SignupRequest, User } from '../../models/User';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatSnackBarModule]
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  isLoading = false;
  hidePassword = true;
  hideConfirmPassword = true;
  returnUrl: string = '/employees';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.signupForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      username: ['', [Validators.required, Validators.minLength(3), Validators.pattern(/^[a-zA-Z0-9_@.-]+$/)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
      confirmPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator });
  }

  ngOnInit(): void {
    if (this.authService.isAuthenticated) {
      this.router.navigate([this.returnUrl]);
      return;
    }
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/employees';
  }

  passwordValidator(control: AbstractControl): { [key: string]: any } | null {
    const value = control.value;
    if (!value) return null;

    const hasNumber = /[0-9]/.test(value);
    const hasUpper = /[A-Z]/.test(value);
    const hasLower = /[a-z]/.test(value);
    const hasSpecial = /[#?!@$%^&*-]/.test(value);
    const valid = hasNumber && hasUpper && hasLower && hasSpecial;

    if (!valid) {
      return { 'passwordStrength': true };
    }
    return null;
  }

  passwordMatchValidator(group: AbstractControl): { [key: string]: any } | null {
    const password = group.get('password');
    const confirmPassword = group.get('confirmPassword');
    
    if (!password || !confirmPassword) return null;
    
    return password.value === confirmPassword.value ? null : { 'passwordMismatch': true };
  }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      this.showNotification('Please fill in all required fields correctly.', 'error');
      return;
    }

    this.isLoading = true;

    const signupData: SignupRequest = {
      firstName: this.signupForm.value.firstName,
      lastName: this.signupForm.value.lastName,
      username: this.signupForm.value.username,
      email: this.signupForm.value.email,
      password: this.signupForm.value.password
    };

    this.authService.signup(signupData).subscribe({
      next: (user: User) => {
        this.isLoading = false;
        
        // Show success notification
        this.showNotification('Account created successfully! Please sign in with your credentials.', 'success');
        
        // Navigate to login page with success message and email
        this.router.navigate(['/login'], {
          queryParams: {
            message: 'signup-success',
            email: signupData.email
          }
        });
      },
      error: (error) => {
        this.isLoading = false;
        console.error('Signup error:', error);
        
        // Handle different error types
        let errorMessage = 'Signup failed. Please try again.';
        
        if (error.status === 409) {
          errorMessage = 'An account with this email or username already exists.';
        } else if (error.status === 400) {
          errorMessage = error.error?.message || 'Invalid signup data. Please check your information.';
        } else if (error.status === 500) {
          errorMessage = 'Server error. Please try again later.';
        } else if (typeof error.error === 'string') {
          errorMessage = error.error;
        }
        
        this.showNotification(errorMessage, 'error');
      }
    });
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  toggleConfirmPasswordVisibility(): void {
    this.hideConfirmPassword = !this.hideConfirmPassword;
  }

  navigateToLogin(): void {
    this.router.navigate(['/login'], { 
      queryParams: { returnUrl: this.returnUrl } 
    });
  }

  getErrorMessage(fieldName: string): string {
    const field = this.signupForm.get(fieldName);
    
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
    
    if (field?.hasError('pattern')) {
      return 'Username can only contain letters, numbers, underscores, @ symbol, dots, and hyphens';
    }
    
    if (field?.hasError('passwordStrength')) {
      return 'Password must contain uppercase, lowercase, number, and special character';
    }
    
    if (fieldName === 'confirmPassword' && this.signupForm.hasError('passwordMismatch')) {
      return 'Passwords do not match';
    }
    
    return '';
  }

  private getFieldDisplayName(fieldName: string): string {
    const displayNames: { [key: string]: string } = {
      'firstName': 'First name',
      'lastName': 'Last name',
      'username': 'Username',
      'email': 'Email',
      'password': 'Password',
      'confirmPassword': 'Confirm password'
    };
    return displayNames[fieldName] || fieldName;
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.signupForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
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
}
