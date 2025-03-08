import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JoinSessionComponent } from './join-session/join-session.component';
import { CurrentSessionComponent } from './current-session/current-session.component';

const routes: Routes = [
  {
    path: '',
    component: JoinSessionComponent
  },
  {
    path: 'session/:sessionId',
    component: CurrentSessionComponent
  },
  {
    path: 'session',
    redirectTo: ''
  },
  {
    path: ':sessionId',
    component: JoinSessionComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
