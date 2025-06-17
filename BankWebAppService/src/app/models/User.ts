export interface User {
  id?: number;
  username: string;
  email: string;
  password?: string;      // optional, usually not sent from backend
  firstName?: string;
  lastName?: string;
  role?: string;
  createdAt?: Date;
  updatedAt?: Date;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface SignupRequest {
  username: string;
  email: string;
  password: string;
  firstName?: string;
  lastName?: string;
}

// Since your backend does NOT return this AuthResponse structure,
// you can remove or comment it out unless you plan to use it later.

export interface AuthResponse {
  success: boolean;
  message: string;
  token?: string;
  user?: User;
  expiresIn?: number;
}
