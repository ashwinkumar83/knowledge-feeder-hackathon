import { Injectable } from '@angular/core';
import { UserModel } from './registration/userModel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  public registerUser(userModel: UserModel): Observable<string> {
    return this.httpClient.post(
      'http://localhost:8080/api/user/register',userModel,{
        responseType: 'text',
      }
    )
  }
}
