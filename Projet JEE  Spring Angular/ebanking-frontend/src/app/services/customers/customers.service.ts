import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of, throwError} from "rxjs";
import {Customer} from "../../model/customer.model";
import {ValidationErrors} from "@angular/forms";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root' //dans la racine de peojet => c'est pas mlaa peine de l'ajouter dans app.module.ts/providers
})
export class CustomersService {
  private backendHost = "http://localhost:8085";
  customers!: Array<Customer>

  constructor(private http: HttpClient) {

  }

  /** Observable in Angular :
   * a feature that provides support for delivering messages between different parts
   * of your single-page application.
   * This feature is frequently used in Angular because it is responsible for handling multiple
   * values, asynchronous programming in Javascript, and also event handling processes.
   */
  public getCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(this.backendHost + "/customers")
  }

  public getCustomer(id: number): Observable<Customer> {
    return this.http.get<Customer>(this.backendHost + "/customers/" + id)
  }

  public searchCustomers(kw: String): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(this.backendHost + "/customers/search?keyword=" + kw)
  }

  public saveCustomers(customer: Customer): Observable<Customer> { // Frontend<-->Backend
    return this.http.post<Customer>(this.backendHost + "/customers", customer);
  }

  public deleteCustomers(id: number) {
    return this.http.delete(this.backendHost + "/customers/" + id);
  }

  getErrorMsgName(field: string, error: ValidationErrors) {
    if (error['required']) {
      return field + " is required";
    } else if (error['minlength']) {
      return field + " should have at least " + error['minlength']['requiredLength'] + " Characters";
    } else return "";
  }

  getErrorMsgEmail(field: string, error: ValidationErrors) {
    if (error['required']) {
      return field + " is required";
    } else if (error['email']) {
      return field + " should have a form of email ";
    } else return "";
  }

  updateCustomer(customer: Customer) {
    return this.http.put(this.backendHost + "/customers/"+customer.id,customer);
  }
}
