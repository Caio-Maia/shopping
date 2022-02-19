import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ClientCreateComponent } from 'src/app/components/users/client/client-create/client-create.component';

@Component({
  selector: 'app-users-crud',
  templateUrl: './users-crud.component.html',
  styleUrls: ['./users-crud.component.css']
})
export class UsersCrudComponent implements OnInit {

  constructor(private router: Router, private dialog:MatDialog) { }

  ngOnInit(): void {
  }

  navigateToClientCreate():void {
    this.router.navigate(['/users/client/create']);
  }

  abrirCadastroCliente():void {
    let dialogClient = this.dialog.open(ClientCreateComponent);
  }

}
