import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MarquesRoutingModule } from './marques-routing.module';
import { MarquesComponent } from './marques.component';

import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    MarquesComponent
  ],
  imports: [
    CommonModule,
    MarquesRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class MarquesModule { }
