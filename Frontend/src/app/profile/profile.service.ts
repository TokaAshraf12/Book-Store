import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProfileInfoResponse, UserEdit } from '../dto/data';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  userProfileInfo(email: string): Observable<ProfileInfoResponse> {
    console.log('Email => ', email)
    return this.http.get<ProfileInfoResponse>(`${environment.apiBaseUrl}/api/user/profile/${email}`)
  }

  public editUser(user: UserEdit) {
    return this.http.post<void>(
      `${environment.apiBaseUrl}/api/user/edit`,
      user,
      {
        observe: 'events',
        reportProgress: true,
      }
    ); 
  }
}
