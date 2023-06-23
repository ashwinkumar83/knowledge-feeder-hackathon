import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InterestDto } from './registration/interestDto';

@Injectable({
  providedIn: 'root',
})
export class InterestService {
  constructor(private httpClient: HttpClient) {}

  public getAllInterests(): Observable<Array<InterestDto>> {
    return this.httpClient.get<Array<InterestDto>>(
      'http://localhost:8080/api/interest/getAllInterests'
    )
  }
}
