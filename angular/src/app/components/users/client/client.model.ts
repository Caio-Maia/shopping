import { Tipo } from "./tipo";

export interface Client {
    id?: number;
    tipo: Tipo;
    nome: string;
    documento: number;
    endereco: string;
    telefone: number;
}