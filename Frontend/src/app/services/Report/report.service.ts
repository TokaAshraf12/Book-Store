import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ErrorStateMatcher } from '@angular/material/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
 

  constructor( private http: HttpClient) { }

  public generateReport(format : String) {
    //  console.log(format);
      console.log(`${environment.apiBaseUrl}/report/${format}`);
     return this.http.get<String>(
      `${environment.apiBaseUrl}/report/${format}`
    );
  }
}
