import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PublisherRequest } from 'src/app/dto/data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {

  constructor(private http: HttpClient) { }

  addPublisher(publisher: PublisherRequest) {
    return this.http.post<void>(
      `${environment.apiBaseUrl}/api/publisher/create`,
      publisher,
      {
        observe: "events",
        reportProgress: true,
      }
    );
  }

  public getAllProducts(): Observable<PublisherRequest[]> {
    return this.http.get<PublisherRequest[]>(
      `${environment.apiBaseUrl}/api/publisher/all`
    );
  }
  
}
