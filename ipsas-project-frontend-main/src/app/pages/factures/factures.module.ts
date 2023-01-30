import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FacturesRoutingModule } from './factures-routing.module';
import { FacturesComponent } from './factures.component';

import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddFactureComponent } from './add-facture/add-facture.component';


@NgModule({
  declarations: [
    FacturesComponent,
    AddFactureComponent
  ],
  imports: [
    CommonModule,
    FacturesRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FacturesModule { }
