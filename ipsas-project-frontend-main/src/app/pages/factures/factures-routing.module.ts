import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FacturesComponent } from './factures.component';
import { AddFactureComponent } from './add-facture/add-facture.component';

const routes: Routes = [
  { path: '', component: FacturesComponent },
  { path: 'new', component: AddFactureComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FacturesRoutingModule { }
