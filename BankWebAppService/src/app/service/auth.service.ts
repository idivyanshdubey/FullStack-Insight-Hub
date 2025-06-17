import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { User, LoginRequest, SignupRequest } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users'; // note changed to /api/users to match your controller
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;
  private userKey = 'current_user';

  constructor(private http: HttpClient) {
    const storedUser = localStorage.getItem(this.userKey);
    this.currentUserSubject = new BehaviorSubject<User | null>(
      storedUser ? JSON.parse(storedUser) : null
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  public get isAuthenticated(): boolean {
    return !!this.currentUserValue;
  }

  login(credentials: LoginRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/login`, credentials);
  }

  signup(userData: SignupRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/signup`, userData);
  }

  logout(): void {
  localStorage.removeItem('current_user'); // or your user storage key
  localStorage.removeItem('auth_token');  // if you store token
  this.currentUserSubject.next(null);      // update observable if any
}


  setUser(user: User): void {
    localStorage.setItem(this.userKey, JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  // Add these methods to your existing AuthService

loginWithGoogle(): Observable<User> {
  // Implement Google OAuth login
  // This is a placeholder - you'll need to implement actual Google OAuth
  return new Observable(observer => {
    // Google OAuth implementation
    observer.error('Google login not implemented yet');
  });
}

loginWithGithub(): Observable<User> {
  // Implement GitHub OAuth login
  // This is a placeholder - you'll need to implement actual GitHub OAuth
  return new Observable(observer => {
    // GitHub OAuth implementation
    observer.error('GitHub login not implemented yet');
  });
}

}
