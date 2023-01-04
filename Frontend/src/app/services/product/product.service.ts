import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import {
  ProductEdit,
  BookResponse,
  BookSpecificDetails,
} from "src/app/dto/data";
import { environment } from "src/environments/environment";
import { LocalStorageService } from "ngx-webstorage";
import { Router } from "@angular/router";
@Injectable({
  providedIn: "root",
})
export class ProductService {
  constructor(
    private http: HttpClient,
    private localStorage: LocalStorageService,
    private router: Router
  ) {}

  public createProduct(product: FormData) {
    return this.http.post<void>(
      `${environment.apiBaseUrl}/api/product/create`,
      product,
      {
        observe: "events",
        reportProgress: true,
      }
    );
  }

  public getProduct(id: number, email: string): Observable<BookResponse> {
    return this.http.get<BookResponse>(
      `${environment.apiBaseUrl}/api/product/${id}/owner/${email}`
    );
  }

  public getAllProducts(): Observable<BookSpecificDetails[]> {
    return this.http.get<BookSpecificDetails[]>(
      `${environment.apiBaseUrl}/api/product/all`
    );
  }

  // Products All Info __ Local Storage
  storageAllInfoForProduct(info: BookResponse) {
    this.localStorage.store("product-all-Info", info);
    if (this.getAllInfo()) {
      this.router.navigateByUrl("/product");
    }
  }

  getAllInfo(): BookResponse {
    return this.localStorage.retrieve("product-all-Info");
  }

  public getProductsByCategory(category: string) {
    return this.http.get<BookSpecificDetails[]>(
      `${environment.apiBaseUrl}/api/filter/${category}`
    );
  }

  public getProductsSorted(sort: string) {
    return this.http.get<BookSpecificDetails[]>(
      `${environment.apiBaseUrl}/api/sort/${sort}`
    );
  }

  public editProduct(product: ProductEdit) {
    return this.http.post<void>(
      `${environment.apiBaseUrl}/api/product/edit`,
      product,
      {
        observe: "events",
        reportProgress: true,
      }
    );
  }
}
