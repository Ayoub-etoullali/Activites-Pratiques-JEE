import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductsService} from "../../services/products/products.service";
import {Product} from "../../model/product.model";

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {
  productFormGroup!: FormGroup;

  constructor(private fb: FormBuilder,public productService:ProductsService) {
  }

  ngOnInit(): void { //prepare form
    this.productFormGroup = this.fb.group({
      name: this.fb.control(null, [Validators.required, Validators.minLength(4)]),
      price: this.fb.control(0, [Validators.required, Validators.min(200)]),
      promotion: this.fb.control(false, [Validators.required]),
    });
  }
  /**
   * What is ngOnInit() Function?
   * The ngOnInit() is a life cycle hook managed by angular
   * and it is called to show that angular is created a component.
   * Actual business logic performed in this method.
   * We need to import OnInIt to use this method.
   *
   * Constructor is used to initialize the class.
   * It doesn’t have any connection with HTML DOM elements.
   * ngOnInit() used to write business logic. Using ngOnInit(),
   * we can perform actions with the HTML DOM elements.
   * Because it is called after the entire component being created.
   * **/

  handleAddProduct() {
    /*console.log(this.productFormGroup.value)*/
    let product = this.productFormGroup.value;
    this.productService.addNewProduct(product).subscribe({
      next : (data)=>{
        alert("Product added successfully")
        this.productFormGroup.reset();
      },
      error : err=>{
        console.log(err)
      }
    });
  }

}
