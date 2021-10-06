import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { ProductCrudComponent } from './views/product-crud/product-crud.component';
import { ProductCreateComponent } from './components/product/product-create/product-create.component';
import { UsersCrudComponent } from './views/users-crud/users-crud.component';
import { SaleCreateComponent } from './components/sale/sale-create/sale-create.component';
import { SaleCrudComponent } from './views/sale-crud/sale-crud.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "products",
    component: ProductCrudComponent
  },
  {
    path: "products/create",
    component: ProductCreateComponent
  },
  {
    path: "sales",
    component: SaleCrudComponent
  },
  {
    path: "sales/create",
    component: SaleCreateComponent
  },
  {
    path: "users",
    component: UsersCrudComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
