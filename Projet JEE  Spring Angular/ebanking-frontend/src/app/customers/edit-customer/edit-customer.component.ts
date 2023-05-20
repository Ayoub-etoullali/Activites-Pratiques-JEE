import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomersService} from "../../services/customers/customers.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../../model/customer.model";

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  customerId!: number;
  editCustomerFormGroup!: FormGroup;
  customer!: Customer;


  constructor(private fb: FormBuilder, public customersService: CustomersService, private route: ActivatedRoute,private  router:Router) { //route : pour obtient le id qui est dans la route
    this.customerId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.customersService.getCustomer(this.customerId).subscribe({
      next: (customer) => {
        this.customer = customer;
        this.editCustomerFormGroup = this.fb.group({
          name: this.fb.control(this.customer.name, [Validators.required, Validators.minLength(4)]),
          email: this.fb.control(this.customer.email, [Validators.required, Validators.email]),
        });
      }, error: err => {
        console.log(err);
      }
    })
  }

  handleUpdateCustomer() {
    let customer = this.editCustomerFormGroup.value;
    customer.id = this.customer.id;
    this.customersService.updateCustomer(customer).subscribe({
      next: (data) => {
        alert("Customer edited successfully")
        this.router.navigateByUrl("/admin/customers")
      },
      error: err => {
        console.log(err)
      }
    });
  }
}
