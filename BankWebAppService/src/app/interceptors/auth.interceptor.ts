// import { Injectable } from '@angular/core';
// import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
// import { Observable, throwError, BehaviorSubject } from 'rxjs';
// import { catchError, filter, take, switchMap } from 'rxjs/operators';
// import { AuthService } from '../service/auth.service';

// @Injectable()
// export class AuthInterceptor implements HttpInterceptor {
//   private isRefreshing = false;
//   private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

//   constructor(private authService: AuthService) {}

//   intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
//     const token = this.authService.getToken();
//     if (token) {
//       request = this.addTokenHeader(request, token);
//     }

//     return next.handle(request).pipe(
//       catchError(error => {
//         if (error instanceof HttpErrorResponse && error.status === 401) {
//           return this.handle401Error(request, next);
//         }
//         return throwError(() => error);
//       })
//     );
//   }

//   private addTokenHeader(request: HttpRequest<any>, token: string): HttpRequest<any> {
//     return request.clone({
//       headers: request.headers.set('Authorization', `Bearer ${token}`)
//     });
//   }

//   private handle401Error(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//     if (!this.isRefreshing) {
//       this.isRefreshing = true;
//       this.refreshTokenSubject.next(null);

//       const token = this.authService.getToken();
//       if (token) {
//         return this.authService.refreshToken().pipe(
//           switchMap((response: any) => {
//             this.isRefreshing = false;
//             if (response.token) {
//               this.refreshTokenSubject.next(response.token);
//               return next.handle(this.addTokenHeader(request, response.token));
//             }
//             return throwError(() => response);
//           }),
//           catchError((err) => {
//             this.isRefreshing = false;
//             this.authService.logout();
//             return throwError(() => err);
//           })
//         );
//       }
//     }

//     return this.refreshTokenSubject.pipe(
//       filter(token => token !== null),
//       take(1),
//       switchMap((token) => next.handle(this.addTokenHeader(request, token)))
//     );
//   }
// }
