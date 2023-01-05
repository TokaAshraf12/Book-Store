import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { RequestOrder } from "src/app/dto/data";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class OrdersService {
  constructor(private http: HttpClient) {}

  public getAllOrders(): Observable<RequestOrder[]> {
    return this.http.get<RequestOrder[]>(
      `${environment.apiBaseUrl}/api/order/all`
    );
  }

  public confirmOrder(id: number) {
    return this.http.get<void>(
      `${environment.apiBaseUrl}/api/order/confirm/${id}`
    );
  }
}
