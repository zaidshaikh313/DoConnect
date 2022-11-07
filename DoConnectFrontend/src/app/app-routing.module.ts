import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAnswerComponent } from './add-answer/add-answer.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ApproveAnswerComponent } from './approve-answer/approve-answer.component';
import { ApproveQuestionComponent } from './approve-question/approve-question.component';
import { AskQuestionComponent } from './ask-question/ask-question.component';
import { ChatComponent } from './chat/chat.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DeleteAnswerComponent } from './delete-answer/delete-answer.component';
import { DeleteQuestionComponent } from './delete-question/delete-question.component';
import { LoginComponent } from './login/login.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { SearchComponent } from './search/search.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'askQuestion',
    component: AskQuestionComponent
  },
  {
    path: 'addAnswer',
    component: AddAnswerComponent
  },
  {
    path: 'search',
    component: SearchComponent
  },

  {
    path: 'chat',
    component: ChatComponent
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'adminDashboard',
    component: AdminDashboardComponent
  },  
  {
    path: 'approveQuestion',
    component: ApproveQuestionComponent
  },  
  {
    path: 'approveAnswer',
    component: ApproveAnswerComponent
  },  
  {
    path: 'deleteQuestion',
    component: DeleteQuestionComponent
  },  
  {
    path: 'deleteAnswer',
    component: DeleteAnswerComponent
  },  

  {
    path: '**',
    component: NoPageFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
