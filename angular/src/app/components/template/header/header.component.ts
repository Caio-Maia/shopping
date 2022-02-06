import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoaderService } from '../loader/loader.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public loaderService: LoaderService, private router?: Router) { }

  ngOnInit(): void {
  }

  location() {
    switch ( this.router.url ) {
      case '/sales':
        return 'Vendas'
      case '/products':
        return 'Produtos'
      case '/users':
        return 'Usuários'
      default: 
        return;
    }
  }
}
