import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Customer} from "../../model/customer.model";
import {CustomersService} from "../../services/customers.service";

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {
  newCustomerFormGroup!: FormGroup

  constructor(private fb: FormBuilder, private customersService: CustomersService) {
  }

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      name: this.fb.control("",[Validators.required,Validators.minLength(4)]),
      email: this.fb.control("",[Validators.required,Validators.email])
    });
  }

  handleSaveCustomer() {
    let customer: Customer = this.newCustomerFormGroup.value;
    this.customersService.saveCustomers(customer).subscribe({
      next : data => {
        alert("Custumer has been successfully saved !!")
      }, error : err => {
        console.log(err)
      }
    });

  }
}
