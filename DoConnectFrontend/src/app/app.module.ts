import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { AskQuestionComponent } from './ask-question/ask-question.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { ChatComponent } from './chat/chat.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AddAnswerComponent } from './add-answer/add-answer.component';
import { SearchComponent } from './search/search.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminHeaderComponent } from './admin-header/admin-header.component';
import { ApproveQuestionComponent } from './approve-question/approve-question.component';
import { ApproveAnswerComponent } from './approve-answer/approve-answer.component';
import { DeleteQuestionComponent } from './delete-question/delete-question.component';
import { DeleteAnswerComponent } from './delete-answer/delete-answer.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    DashboardComponent,
    HeaderComponent,
    AskQuestionComponent,
    NoPageFoundComponent,
    ChatComponent,
    AddAnswerComponent,
    SearchComponent,
    AdminDashboardComponent,
    AdminHeaderComponent,
    ApproveQuestionComponent,
    ApproveAnswerComponent,
    DeleteQuestionComponent,
    DeleteAnswerComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
