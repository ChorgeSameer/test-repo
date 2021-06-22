import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductDataService } from '../service/data/product-data.service';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

export class Product {
 constructor(
   public id: number,
   public name: string,
   public sku: string,
   public description: string,
   public price: number,
   public stock: number
 ){}
}

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[]

  message: string
  constructor(
    private router : Router,
    private productService : ProductDataService,
    private basicAuthenticationService : BasicAuthenticationService
  ) { }

  ngOnInit() {
    this.refreshProducts();
  }

  refreshProducts(){
    this.productService.retrieveAllProducts(this.basicAuthenticationService.getAuthenticatedUser()).subscribe(
      response => {
        console.log(response);
        this.products = response;
      }
    )
  }

  deleteProduct(id) {
    console.log(`delete product ${id}` )
    this.productService.deleteProducts(this.basicAuthenticationService.getAuthenticatedUser(), id).subscribe (
      response => {
        console.log(response);
        this.message = `Delete of Product ${id} Successful!`;
        this.refreshProducts();
      }
    )
  }

  updateProduct(id) {
    console.log(`update ${id}`)
    this.router.navigate(['products',id])
  }
}
