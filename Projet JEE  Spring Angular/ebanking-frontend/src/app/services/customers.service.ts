import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";

@Injectable({
  providedIn: 'root' //dans la racine de peojet => c'est pas mlaa peine de l'ajouter dans app.module.ts/providers
})
export class CustomersService {
private backendHost="http://localhost:8085";
  constructor(private http:HttpClient) {

  }

  /** Observable in Angular :
   * a feature that provides support for delivering messages between different parts
   * of your single-page application.
   * This feature is frequently used in Angular because it is responsible for handling multiple
   * values, asynchronous programming in Javascript, and also event handling processes.
   */
  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(this.backendHost+"/customers")
  }
  public searchCustomers(kw:String):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(this.backendHost+"/customers/search?keyword="+kw)
  }
  public saveCustomers(customer:Customer):Observable<Customer>{ // Frontend<-->Backend
    return this.http.post<Customer>(this.backendHost+"/customers",customer);
  }
  public deleteCustomers(id:number){
    return this.http.delete(this.backendHost+"/customers/"+id);
  }
}
