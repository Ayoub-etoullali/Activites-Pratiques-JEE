import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomersService} from "../services/customers.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers!: Observable<Array<Customer>>;
  errorMsg!: String;
  searchFormGroup!: FormGroup;

  constructor(private customersService: CustomersService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {

    this.searchFormGroup = this.formBuilder.group({
      keyword: this.formBuilder.control("")
    });

    this.handleSearchCustomers();

    /*
    [M1] Error : dans HTML on fait subscribe
    [M2] Error
    .subscribe({ // j'attend
    next: data => {
      this.customers = data;
    }, error: error => {
      this.errorMsg = error.message;
    }
  });
     */
  }

  handleSearchCustomers() {
    let kw = this.searchFormGroup.value.keyword; // add ?
    this.customers = this.customersService.searchCustomers(kw).pipe(
      catchError(err => {
        this.errorMsg = err.message;
        return throwError(err)
      })
    );
  }

  handleDeleteCustomer(customer: Customer) {
    let conf=confirm("Are you sure?")
    if (!conf) return;
    this.customersService.deleteCustomers(customer.id).subscribe({
      next: value => {
        // [M1]
        this.customers = this.customers.pipe(
          map(data => {
            let index=data.indexOf(customer);
            data.slice(index,1);
            return data
          })
        );
         // [M2] refresh
        //this.handleSearchCustomers()
      }, error: err => {
        console.log(err)
      }
    })
  }
}
