import {Injectable} from '@angular/core';
import {Observable, of, throwError} from "rxjs";
import {PageProduct, Product} from "../../model/product.model";
// @ts-ignore
import * as uuid from "uuid";
import {UUID} from "angular2-uuid";
import {ValidationErrors} from "@angular/forms";

@Injectable({
  providedIn: 'root'
  /*
  - Ce service est disponible dans la racine de projet
  - c'est pas besoin de le déclarer dans <providers> [app.module.ts]
   */
})
export class ProductsService {
  private products!: Array<Product>;

  constructor() {
    //=> initialiser le tableau
    this.products = [
      //contient les objets js
      {id: uuid.v4(), name: "Computer", price: 5000, promotion: true},
      {id: uuid.v4(), name: "Printer", price: 3000, promotion: false},
      {id: uuid.v4(), name: "Smart Phone", price: 4000, promotion: true}
    ];
    for (let i = 0; i < 10; i++) {
      this.products.push({id: uuid.v4(), name: "Computer", price: 5000, promotion: true})
      this.products.push({id: uuid.v4(), name: "Printer", price: 3000, promotion: false})
      this.products.push({id: uuid.v4(), name: "Smart Phone", price: 4000, promotion: true})
    }
  }

  public getProducts(): Observable<Array<Product>> { //Product[]
    let random = Math.random();
    if (random < 0.1) return throwError(() => new Error("Internet connection error"));
    else return of(this.products);
  }

  public getPageProducts(page: number, size: number): Observable<PageProduct> { //Product[]
    let index = page * size;
    let totalPages = ~~(this.products.length / size); // ~~ : pas de virgule
    if (this.products.length % size != 0) totalPages++;
    let pageProducts = this.products.slice(index, index + size); // slice : donne un partie du tableau
    return of({page: page, size: size, totalPages: totalPages, products: pageProducts});
  }

  public deleteProduct(id: string): Observable<boolean> {
    this.products = this.products.filter(p => p.id != id);
    return of(true);
  }

  public setPromotion(id: string): Observable<boolean> {
    let product = this.products.find(p => p.id == id);
    if (product != undefined) {
      product.promotion = !product.promotion;
      return of(true);
    } else return throwError(() => new Error("Product not found"))
  }

  public searchProduct(keyword: string, page: number, size: number): Observable<PageProduct> {
    let result = this.products.filter(p => p.name.includes(keyword));
    let index = page * size;
    let totalPages = ~~(result.length / size);
    if (this.products.length % size != 0) totalPages++;
    let pageProducts = result.slice(index, index + size);
    return of({page: page, size: size, totalPages: totalPages, products: pageProducts});
  }

  public addNewProduct(product: Product): Observable<Product> {
    product.id = UUID.UUID();
    this.products.push(product);
    return of(product);
  }

  public EditProduct( product: Product): Observable<Product> {
    this.products=this.products.map(p=>(p.id==product.id)?product:p);
    return of(product);
  }

  public getProduct(id: string): Observable<Product> {
    let product = this.products.find(p => p.id == id);
    if (product == undefined) {
      throwError(() => new Error("Product not found"));
      // @ts-ignore
      return of(undefined);
    }
    return of(product);
  }

  getErrorMsgName(field: string, error: ValidationErrors) {
    if (error['required']) {
      return field + " is required";
    } else if (error['minlength']) {
      return field + " should have at least " + error['minlength']['requiredLength'] + " Characters";
    } else return "";
  }

  getErrorMsgPrice(field: string, error: ValidationErrors) {
    if (error['required']) {
      return field + " is required";
    } else if (error['min']) {
      return field + " should have min value " + error['min']['min'];
    } else return "";
  }
}
