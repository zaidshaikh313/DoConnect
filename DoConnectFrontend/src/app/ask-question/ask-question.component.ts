import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { ApiService } from "../Service/api.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-ask-question",
  templateUrl: "./ask-question.component.html",
  styleUrls: ["./ask-question.component.css"],
})
export class AskQuestionComponent implements OnInit {
  addQuestionForm: FormGroup;
  userName: string;

  constructor(
    private http: HttpClient,
    private api: ApiService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.addQuestionForm = new FormGroup({
      title: new FormControl(null),
      question: new FormControl(null),
    });
    this.userName = this.api.getUserName();
    if (this.userName === undefined) {
      alert("Login is required");
      this.router.navigate(["/login"]);
    }
  }
  addQuestion() {
    let requestBody = this.addQuestionForm?.value;
    requestBody["username"] = this.userName;
    this.http
      .post<any>("http://localhost:9090/questions/add", requestBody)
      .subscribe(
        (res) => {
          alert("Question is posted for approval");

          this.addQuestionForm?.reset();
        },
        (err) => {
          alert("Something went wrong");
          this.addQuestionForm?.reset();
        }
      );
  }
}
