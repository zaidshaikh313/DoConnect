import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-approve-answer',
  templateUrl: './approve-answer.component.html',
  styleUrls: ['./approve-answer.component.css']
})
export class ApproveAnswerComponent implements OnInit {
  questions:any = [];
  answers : any=[];

  constructor(
   private http : HttpClient
  ) { }

  ngOnInit(): void {
    this.loadAnswers();
  }

  loadAnswers(){
    this.questions.splice(0);
    this.answers.splice(0);
    this.http.get<any>("http://localhost:9090/answers/notApproved").subscribe(
      (res) => {

        for (let i = 0; i < res.length; i++) {

      

          this.questions.push(res[i].questionDto);
          for (let j = 0; j < res[i].answerDtos.length; j++) {
            console.log(res[i].answerDtos[j]);
            this.answers.push(res[i].answerDtos[j]);
          }

        }
        // console.log(this.questions);


      }, (err) => {
        alert("Something went wrong");
      }
    );


  }

  approve(aid){
    this.http.post<any>("http://localhost:9090/admin/approveAnswer/"+aid,null).subscribe((res) => {
      console.log(res);
      alert(res.message);
      this.loadAnswers();

    }, (err) => {
      console.log("Something went wrong");
    });



  }

}
