import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { EmployeeListComponent } from './component/employee-list/employee-list.component';
import { AuthGuard } from './guards/auth.guard'; // create this guard to protect routes

const routes: Routes = [
  { 
    path: 'login', 
    component: LoginComponent,
    data: { animation: 'LoginPage' }
  },
  { 
    path: 'signup', 
    component: SignupComponent,
    data: { animation: 'SignupPage' }
  },
  { 
    path: 'employees', 
    component: EmployeeListComponent, 
    canActivate: [AuthGuard],
    data: { animation: 'EmployeesPage' }
  },
  { 
    path: '', 
    redirectTo: '/login', 
    pathMatch: 'full' 
  },
  { 
    path: '**', 
    redirectTo: '/login' 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    // Enable router animations
    enableTracing: false, // Set to true for debugging
    // Scroll to top on route change
    scrollPositionRestoration: 'top'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
