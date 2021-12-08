import { Component, OnDestroy, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ProductService } from '../product.service';
import { h, UserConfig } from "gridjs";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-product-read',
  templateUrl: './product-read.component.html',
  styleUrls: ['./product-read.component.css']
})
export class ProductReadComponent implements OnInit, OnDestroy {

  private products: Product[];
  private ngUnsubscribe: Subscription;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.ngUnsubscribe = this.productService.read().subscribe(products => {
      this.products = products;
    }, (error)=> {
      this.productService.showMenssage('Não Foi Possivel encontrar nenhum produto.');
    })
  }

  ngOnDestroy():void {
    this.ngUnsubscribe.unsubscribe();
  }

  public gridConfig: UserConfig = {
    columns: [
      {name:"Id", hidden: true }, 
      {name:"Nome"}, 
      {name:"Preço", formatter: (cell) => `R$ ${cell}`}, 
      {name:"Quantidade"},
      { 
        name: 'Ações',
        columns: [
        {
          name: 'Deletar',
          sort: false,
          formatter: (cell, row) => {
            return h('button', {
              class: 'material-icons',
              onClick: () => this.productService.delete(row.cells[0].data)
            }, 'delete_outline');
          },
        },
        {
          name: 'Editar',
          sort: false,
          formatter: (cell, row) => {
            return h('button', {
              class: 'material-icons',
              onClick: () => alert("ainda será feito")
            }, 'edit');
          }
        },
      ]
      }
    ],
    sort: true,
    search: true,
    fixedHeader: true,
    pagination: {
      enabled: true,
      limit: 10
    },
    server: {
      url: 'http://localhost:8080/produtos',
      then: data => data.map(produto => [produto.id, produto.nome, produto.preco, produto.quantidade])
    },
    style: {
      th: {
        'text-align': 'center'
      },
      td: {
        'text-align': 'center'
      }
    },
    language: {
      'search': {
        'placeholder': '🔍 Buscar...'
      },
      'pagination': {
        'previous': 'Anterior',
        'next': 'Proxima',
        'showing': 'Mostrando',
        'of': 'de',
        'to': 'a',
        'results': () => 'Produtos'
      },
      'noRecordsFound':'Nenhum dado encontrado',
      'error':'Ocorreu um erro ao tentar se comunicar com o servidor',
      'loading': 'Carregando...'
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
