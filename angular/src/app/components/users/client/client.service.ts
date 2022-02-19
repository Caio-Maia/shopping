import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ComponentChild } from 'preact';
import { Observable } from 'rxjs';
import { Client } from './client.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  baseUrl = 'http://localhost:8080/clientes'

  constructor(private snackBar: MatSnackBar,
    private http: HttpClient) { }

  showMenssage(msg: string, duration = 4000, actionText = 'Fechar') {
    return this.snackBar.open(msg, actionText, {
      duration: duration,
      horizontalPosition: 'end',
      verticalPosition: 'top'
    });
  }

  create(client: Client): Observable<Client> {
    return this.http.post<Client>(this.baseUrl, client);
  }

  read(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseUrl);
  }

  delete(clientId: ComponentChild) {
    alert('Deletando cliente de id: '+clientId.toString());
    return this.http.delete(this.baseUrl+`/${clientId}`).subscribe({
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
