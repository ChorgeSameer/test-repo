import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product-list/product-list.component';
import { ProductDataService } from '../service/data/product-data.service';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  id : number
  username : string
  product : Product

  constructor(
    private route : ActivatedRoute,
    private router : Router,
    private productService : ProductDataService,
    private basicAuthenticationService : BasicAuthenticationService
  ) { }

  ngOnInit() {

    this.id = this.route.snapshot.params['id'];
    this.product = new Product(this.id,'','','',0,0);
    if(this.id!= undefined) {
      this.productService.retrieveProduct(this.username, this.id)
          .subscribe (
            data => this.product = data
          )
    }
  }

  saveProduct() {
    if(this.id == undefined) { //=== ==
      this.productService.createProduct(this.basicAuthenticationService.getAuthenticatedUser(), this.product)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['products'])
            }
          )
    } else {
      this.productService.updateProduct(this.basicAuthenticationService.getAuthenticatedUser(), this.id, this.product)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['products'])
            }
          )
    }
  }

  refresh(): void { window.location.reload(); }
}
