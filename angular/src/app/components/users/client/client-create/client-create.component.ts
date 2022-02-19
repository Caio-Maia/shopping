import { Component, OnInit, Provider } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Client } from '../client.model';
import { ClientService } from '../client.service';
import { Tipo } from '../tipo';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css']
})
export class ClientCreateComponent implements OnInit {

  client:Client = {
    nome: undefined,
    tipo: undefined,
    documento: undefined,
    endereco: undefined,
    telefone: undefined
  }

  allTipos = Tipo;

  filteredOptions: Observable<Provider[]>;
  selectedProvider: string;
  clientForm: FormGroup;

  constructor(private clientService: ClientService,
    private router: Router) { }

  ngOnInit(): void {
    this.clientForm = new FormGroup({
      nome: new FormControl(this.client.nome, [
        Validators.required
      ]),
      tipo: new FormControl(this.client.tipo, [
        Validators.required
      ]),
      cpf: new FormControl(this.client.documento),
      cnpj: new FormControl(this.client.documento),
      endereco: new FormControl(this.client.endereco, [
        Validators.required
      ]),
      telefone: new FormControl(this.client.telefone, [
        Validators.required,
        //Validators.pattern('/^\([1-9]{2}\)[0-9]{4,5}-[0-9]{4}$/')
      ])
    })
  }

  createClient(): void {
    if(!this.clientForm.valid) {
      this.clientForm.markAllAsTouched();
      return;
    } 
    this.clientService.create(this.client).subscribe(() => {
      this.clientService.showMenssage('Cliente cadastrado com sucesso!');
      this.router.navigate(['/users']);
    }, (error)=> {
      this.clientService.showMenssage('Não Foi Possivel Cadastrar o cliente.');
    })
  }

  cancel(): void {
    this.router.navigate(['/users']);
  }

  typeForm() {
    this.clientForm.get('tipo').valueChanges
    .subscribe(value => {
      if(value == this.allTipos.PESSOA_FISICA) {
        this.clientForm.get('cpf').setValidators([Validators.required, Validators.pattern('[0-9]{3}\.?[0-9]{3}\.?[0-9]{3}\-?[0-9]{2}')]);
        this.clientForm.get('cnpj').clearValidators();
      }
      if(value == this.allTipos.PESSOA_JURIDICA) {
        this.clientForm.get('cnpj').setValidators([Validators.required, Validators.pattern('[0-9]{2}\.?[0-9]{3}\.?[0-9]{3}\/?[0-9]{4}\-?[0-9]{2}')]);
        this.clientForm.get('cpf').clearValidators();
      }
      this.clientForm.get('cpf').updateValueAndValidity({onlySelf: true});
      this.clientForm.get('cnpj').updateValueAndValidity({onlySelf: true});
      return;
    }
    );
  }

  compareType(tipo:Tipo) {
    return this.client.tipo == tipo;
  }

}
