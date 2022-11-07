import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-question',
  templateUrl: './delete-question.component.html',
  styleUrls: ['./delete-question.component.css']
})
export class DeleteQuestionComponent implements OnInit {
  questions: any = [];

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit(): void {

    this.loadQuestions();
  }
  loadQuestions() {
    this.http.get<any>("http://localhost:9090/questions/all").subscribe((res) => {
      this.questions = res;

    }, (err) => {
      console.log("Something went wrong");
    });


  }

  delete(qid) {
    this.http.delete<any>("http://localhost:9090/admin/deleteQuestion/" + qid).subscribe((res) => {

      alert(res.message);
      this.ngOnInit();

    }, (err) => {
      console.log("Something went wrong");
    });


  }

}
