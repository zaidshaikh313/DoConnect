import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-answer',
  templateUrl: './delete-answer.component.html',
  styleUrls: ['./delete-answer.component.css']
})
export class DeleteAnswerComponent implements OnInit {
  questions : any=[];
  answers : any = [];

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.getAnswers();

  }
  getAnswers(){
    this.questions.splice(0);
    this.answers.splice(0);
    this.http.get<any>("http://localhost:9090/answers/all").subscribe(
      (res) => {

        for (let i = 0; i < res.length; i++) {
          this.questions.push(res[i].questionDto);
          for (let j = 0; j < res[i].answerDtos.length; j++) {
            console.log(res[i].answerDtos[j]);
            this.answers.push(res[i].answerDtos[j]);
          }

        }
      

      }, (err) => {
        alert("Something went wrong");
      }
    );


  }
  delete(aid){
    this.http.delete<any>("http://localhost:9090/admin/deleteAnswer/"+aid).subscribe(
      (res) => {
        alert(res.message);
        this.ngOnInit();

       

      }, (err) => {
        alert("Something went wrong");
      }
    );


  }

}
