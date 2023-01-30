import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReglementsRoutingModule } from './reglements-routing.module';
import { ReglementsComponent } from './reglements.component';

import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ReglementsComponent
  ],
  imports: [
    CommonModule,
    ReglementsRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ReglementsModule { }
