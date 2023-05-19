import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {ProductsService} from "../../services/products/products.service";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../model/product.model";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  productId!: string;
  productFormGroup!: FormGroup;
  product! : Product;

  constructor(private fb:FormBuilder,public productService: ProductsService, private route: ActivatedRoute) { //route : pour obtient le id qui est dans la route
    this.productId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.productService.getProduct(this.productId).subscribe({
      next : (product)=>{
        this.product=product;
        this.productFormGroup = this.fb.group({
          name: this.fb.control(this.product.name, [Validators.required, Validators.minLength(4)]),
          price: this.fb.control(this.product.price, [Validators.required, Validators.min(200)]),
          promotion: this.fb.control(this.product.promotion, [Validators.required]),
        });
      }, error : err => {
         console.log(err);
      }
    })
  }

  handleEditProduct() {
    /*console.log(this.productFormGroup.value)*/
    let product = this.productFormGroup.value;
    product.id = this.product.id;
    this.productService.EditProduct(product).subscribe({
      next: (data) => {
        alert("Product edited successfully")
      },
      error: err => {
        console.log(err)
      }
    });
  }


}
