import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar'
import { ComponentChild } from 'preact';
import { Observable } from 'rxjs';
import { Product } from './product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl = 'http://localhost:8080/produtos'

  constructor(private snackBar: MatSnackBar,
    private http: HttpClient) { }

  showMenssage(msg: string, duration = 4000, actionText = 'Fechar') {
    return this.snackBar.open(msg, actionText, {
      duration: duration,
      // Cast the variable to MatSnackBarHorizontalPosition
      horizontalPosition: 'end',
      verticalPosition: 'top'
    });
  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.baseUrl, product);
  }

  read(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl);
  }

  delete(productId: ComponentChild) {
    alert('Deletando produto de id: '+productId.toString());
    return this.http.delete(this.baseUrl+`/${productId}`).subscribe({
      next: () => {
        window.location.reload();
      },
      error: error => {
        this.showMenssage('Não foi possivel deletar!');
        console.error(error);
      }
    });
  }
}
