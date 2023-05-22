import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Vol} from "../../model/vol.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VolService {
  private backendHost = "http://localhost:8085";

  constructor(private http: HttpClient) {

  }

  searchVols(kw: String): Observable<Array<Vol>> {
      return this.http.get<Array<Vol>>(this.backendHost + "/vols/search?keyword=" + kw)
  }
  getAllVols(): Observable<Array<Vol>> {
    return this.http.get<Array<Vol>>(this.backendHost + "/vols/all")
  }
}
