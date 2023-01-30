import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TaxesRoutingModule } from './taxes-routing.module';
import { TaxesComponent } from './taxes.component';

import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    TaxesComponent
  ],
  imports: [
    CommonModule,
    TaxesRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class TaxesModule { }
