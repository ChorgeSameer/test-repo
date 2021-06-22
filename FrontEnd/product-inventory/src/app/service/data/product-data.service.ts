import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { Product } from 'src/app/product-list/product-list.component';

@Injectable({
  providedIn: 'root'
})
export class ProductDataService {

  constructor(private http:HttpClient) {
    
   }

   retrieveAllProducts(username) {
    return this.http.get<Product[]>(`${API_URL}/users/${username}/products`);
    //console.log("Execute Hello World Bean Service")
  }

  deleteProducts(username, id){
    return this.http.delete(`${API_URL}/users/${username}/products/${id}`);
  }

  retrieveProduct(username, id){
    return this.http.get<Product>(`${API_URL}/users/${username}/products/${id}`);
  }

  updateProduct(username, id, product){
    return this.http.put(
          `${API_URL}/users/${username}/products/${id}`
                , product);
  }

  createProduct(username, product){
    return this.http.post(
              `${API_URL}/users/${username}/products`
                , product);
  }
}
