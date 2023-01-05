import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookSpecificDetails } from 'src/app/dto/data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getManagerOwnedProducts(email: string) {
    return this.http.get<BookSpecificDetails[]>(
      `${environment.apiBaseUrl}/api/user/owned/products/${email}`
    )
  }

  public getCustomerPurchasedProducts(email: string) {
    return this.http.get<BookSpecificDetails[]>(
      `${environment.apiBaseUrl}/api/user/purchased/products/${email}`
    )
  }

  public promoteUser(email: string) {
    return this.http.get<void>(
      `${environment.apiBaseUrl}/api/user/promote/${email}`
    )
  }
}
