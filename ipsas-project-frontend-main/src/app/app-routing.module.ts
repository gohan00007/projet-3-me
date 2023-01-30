import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/dashboard' },
  { path: 'clients', loadChildren: () => import('./pages/clients/clients.module').then(m => m.ClientsModule) },
  { path: 'categories', loadChildren: () => import('./pages/categories/categories.module').then(m => m.CategoriesModule) },
  { path: 'marques', loadChildren: () => import('./pages/marques/marques.module').then(m => m.MarquesModule) },
  { path: 'taxes', loadChildren: () => import('./pages/taxes/taxes.module').then(m => m.TaxesModule) },
  { path: 'reglements', loadChildren: () => import('./pages/reglements/reglements.module').then(m => m.ReglementsModule) },
  { path: 'produits', loadChildren: () => import('./pages/produits/produits.module').then(m => m.ProduitsModule) },
  { path: 'factures', loadChildren: () => import('./pages/factures/factures.module').then(m => m.FacturesModule) },
  { path: 'dashboard', loadChildren: () => import('./pages/dashboard/dashboard.module').then(m => m.DashboardModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
