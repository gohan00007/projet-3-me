import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsComponent } from './clients.component';
import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ClientsComponent
  ],
  imports: [
    CommonModule,
    ClientsRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ClientsModule { }
