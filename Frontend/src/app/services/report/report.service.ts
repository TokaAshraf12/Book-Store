import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class ReportService {
  constructor(private http: HttpClient) {}

  public generateReport5(format: string) {
    return this.http.get<void>(
      `${environment.apiBaseUrl}/api/report/top-5-customers/${format}`
    );
  }

  public generateReport10(format: string) {
    return this.http.get<void>(
      `${environment.apiBaseUrl}/api/report/top-10-selling/${format}`
    );
  }

  public generateReportTotal(format: string) {
    return this.http.get<void>(
      `${environment.apiBaseUrl}/api/report/top-sells/${format}`
    );
  }
}
