import {Component, OnInit} from '@angular/core';
import {ProductsService} from "../services/products/products.service";
import {Product} from "../model/product.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  /*
  - any : n'impote qu'elle type
  - il faut initialiser :
      [M1] undefined (revient aucun valeur) ** products : Array<any> | undefined;
      [M2] ! (je veux laisse comme ça) ** products! : Array<any>;
   */
  products!: Array<Product>;
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  errorMessage!: String;
  searchFormGroup!: FormGroup;
  currentAction: string = "all";

  constructor(private productsService: ProductsService,
              private formBuilder: FormBuilder,
              public  authService : AuthenticationService,
              private router:Router) {
  }

  ngOnInit(): void { //exécute au démarrage lorsque le rendu (composant) est générer
    this.searchFormGroup = this.formBuilder.group({
      keyword: this.formBuilder.control(null)
    })
    this.handleGetPageProducts();
  }

  handleGetProducts() {
    this.productsService.getProducts().subscribe({ //Programmation Asynchrone
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
        this.errorMessage = err;
      }
    });
  }

  handleGetPageProducts() {
    this.productsService.getPageProducts(this.currentPage, this.pageSize).subscribe({ //Programmation Asynchrone
      next: (data) => {
        this.products = data.products;
        this.totalPages = data.totalPages;
      },
      error: (err) => {
        this.errorMessage = err;
      }
    });
  }

  handleDeleteProduct(p: Product) {
    let confirmer = confirm("Are you sure?");
    if (!confirmer) return;
    this.productsService.deleteProduct(p.id).subscribe({
      next: data => {
        //pas pratique : this.handleGetAllProducts();
        let index = this.products.indexOf(p);
        this.products.splice(index, 1)

      },
      error: err => {

      }
    })
  }

  handleSetPromotion(p: Product) {
    let promo = p.promotion;
    this.productsService.setPromotion(p.id).subscribe({
      next: () => {
        p.promotion = !promo;
      },
      error: (err) => {
        this.errorMessage = err;
      }
    })
  }

  handlSearchProduct() {
    this.currentAction = "search";
    // this.currentPage=0;
    let keyword = this.searchFormGroup.value.keyword;
    let page = this.currentPage;
    let size = this.pageSize;
    this.productsService.searchProduct(keyword, page, size).subscribe({
      next: (data) => {
        this.products = data.products;
        this.totalPages = data.totalPages;
      }
    });
  }

  goToPage(i: number) {
    this.currentPage = i;
    if (this.currentAction == "all")
      this.handleGetPageProducts();
    else {
      this.handlSearchProduct();
    }
  }

  handleNewProduct() {
    this.router.navigateByUrl("/admin/newProduct");
  }

  handleEditProduct(p: Product) {
    this.router.navigateByUrl("/admin/editProduct/"+p.id);
  }


}
