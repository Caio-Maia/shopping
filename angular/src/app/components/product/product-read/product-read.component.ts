import { Component, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ProductService } from '../product.service';
import { UserConfig } from "gridjs";

@Component({
  selector: 'app-product-read',
  templateUrl: './product-read.component.html',
  styleUrls: ['./product-read.component.css']
})
export class ProductReadComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.read().subscribe(products => {
      this.products = products;
    }, (error)=> {
      this.productService.showMenssage('Não Foi Possivel encontrar nenhum produto.');
    })
  }

  public gridConfig: UserConfig = {
    columns: ["Nome", "Quantidade", "Preço"],
    pagination: {
      enabled: true,
      limit: 5,
      server: {
        url: (prev, page, limit) => `${prev}?limit=${limit}&offset=${page * limit}`
      }
    },
    server: {
      url: 'http://localhost:8080/produtos',
      then: data => data.content.map(produto => [produto.nome, produto.quantidade, produto.preco]),
      total: data => data.numberOfElements
    }
  };

  handleCellClick(event: any) {
    console.log("cellClicked", event);
  }

  handleRowClick(event: any) {
    console.log("rowClicked", event);
  }

  handleBeforeLoad(event: any) {
    console.log("beforeLoad", event);
  }

  handleGridLoad(event: any) {
    console.log("load", event);
  }

}
