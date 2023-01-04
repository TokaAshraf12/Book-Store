import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookSpecificDetails } from 'src/app/dto/data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  getProductsByWord(word: string): Observable<BookSpecificDetails[]> {
    return this.http.get<BookSpecificDetails[]>(
      `${environment.apiBaseUrl}/api/search/${word}`
    );
  }
}
