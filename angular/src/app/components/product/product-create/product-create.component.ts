import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Router } from '@angular/router'
import { Product } from '../product.model';
import { Provider } from '../../provider/provider.model';
import { Observable } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  product:Product = {
    nome: undefined,
    preco: undefined,
    quantidade: undefined
  }

  providers:Provider[] = [
    {nome:"Vagabundo INC", cnpj:"123712", endereco:"rua dos doidos", telefone:"89346232"},
    {nome:"MACMAC INC", cnpj:"123712", endereco:"rua dos doidos", telefone:"89346232"},
    {nome:"DADA INC", cnpj:"123712", endereco:"rua dos doidos", telefone:"89346232"}
  ]

  myControl = new FormControl();
  filteredOptions: Observable<Provider[]>;
  selectedProvider: string;
  produtoForm: FormGroup;

  constructor(private productService: ProductService,
    private router: Router) { }

  ngOnInit(): void {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
    this.produtoForm = new FormGroup({
      nome: new FormControl(this.product.nome, [
        Validators.required
      ]),
      preco: new FormControl(this.product.preco, [
        Validators.pattern('\d+(,\d{1,2})?'),
        Validators.required
      ]),
      quantidade: new FormControl(this.product.quantidade, [
        Validators.pattern("^[0-9]*$"),
        Validators.required
      ])
    })
  }

  private _filter(value: string): Provider[] {
    const filterValue = value.toLowerCase();

    return this.providers.filter(option => option.nome.toLowerCase().includes(filterValue));
  }

  createProduct(): void {
    if(!this.produtoForm.valid) {
      this.produtoForm.markAllAsTouched();
      return;
    } 
    this.productService.create(this.product).subscribe(() => {
      this.productService.showMenssage('Produto cadastrado com sucesso!');
      this.router.navigate(['/products']);
    })
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }

}
