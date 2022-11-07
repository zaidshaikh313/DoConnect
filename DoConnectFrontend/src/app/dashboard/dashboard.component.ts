import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { map } from "rxjs";
import { ApiService } from "../Service/api.service";
import { QuestionModel } from "../model/question.model";
import { AnswerModel } from "../model/answer.model";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"],
})
export class DashboardComponent implements OnInit {
  question: QuestionModel;
  questions: any = [];
  answers: any = [];
  
  constructor(
    private api: ApiService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllQA();
   
  }
  getData() {
    console.log("Hii");
    this.api.getApprovedQuestions().subscribe((res) => {
      // console.log(res);
      this.questions = res;
    });
  }
  addAnswer(q_id) {
    this.api.setQid(q_id);
    this.router.navigate(["addAnswer"]);
  }

  getAllQA() {
    console.log("hii");
    this.http
      .get<any>("http://localhost:9090/questions/approved/true")
      .subscribe(
        (res) => {
          for (let i = 0; i < res.length; i++) {
            this.question = res[i].questionDto;

            this.questions.push(this.question);
            for (let j = 0; j < res[i].answerDtos.length; j++) {
              // console.log(res[i].answerDtos[j]);
              if (res[i].answerDtos[j].returnedImage != null) {
                res[i].answerDtos[j].returnedImage = this.convertImage(
                  res[i].answerDtos[j].returnedImage
                );
              }
              this.answers.push(res[i].answerDtos[j]);
            }
          }
        },
        (err) => {
          alert("Something went wrong");
        }
      );
  }
  convertImage(img: any) {
    return "data:image/jpeg;base64," + img;
  }
}
