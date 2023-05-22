import {Component, OnInit} from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {Vol} from "../../model/vol.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {Router} from "@angular/router";
import {VolService} from "../../services/vol/vol.service";

@Component({
  selector: 'app-vol',
  templateUrl: './vol.component.html',
  styleUrls: ['./vol.component.css']
})
export class VolComponent implements OnInit {
  vols!: Observable<Array<Vol>>;
  errorMsg!: String;
  searchFormGroup!: FormGroup;

  constructor(public  authService : AuthenticationService, private volService: VolService, private formBuilder: FormBuilder,private router:Router) {
  }

  ngOnInit(): void {

    this.searchFormGroup = this.formBuilder.group({
      keyword: this.formBuilder.control("")
    });

    this.handleSearchVols();
    this.getAllVols();

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

  handleSearchVols() {
    let kw = this.searchFormGroup.value.keyword; // add ?
    this.vols = this.volService.searchVols(kw).pipe(
      catchError(err => {
        this.errorMsg = err.message;
        return throwError(err)
      })
    );
  }
/*
  handleDeleteCustomer(customer: Customer) {
    let conf=confirm("Are you sure?")
    if (!conf) return;
    this.customersService.deleteCustomers(customer.id).subscribe({
      next: value => {
        // [M1]
        this.vols = this.vols.pipe(
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


  handleCustomerAccounts(customer: Customer) {
    this.router.navigateByUrl("/admin/customer-accounts/"+customer.id,{state :customer});
  }

  handleUpdateCustomer(c: Customer) {
    this.router.navigateByUrl("/admin/edit-customer/"+c.id);

  }
*/
  private getAllVols() {

  }
}
