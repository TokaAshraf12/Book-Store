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

  public generateReport5(format : String):Observable<String> {
     return this.http.get<String>(
      `${environment.apiBaseUrl}/report5/${format}`
    );
  }

  public generateReport10(format : String):Observable<String>{
     return this.http.get<String>(
     `${environment.apiBaseUrl}/report10/${format}`
   );
 }
 public generateReportTotal(format : String):Observable<String> {
  return this.http.get<String>(
   `${environment.apiBaseUrl}/reportTotal/${format}`
 );
}
}
