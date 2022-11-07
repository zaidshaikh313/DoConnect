import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: "app-add-answer",
  templateUrl: "./add-answer.component.html",
  styleUrls: ["./add-answer.component.css"],
})
export class AddAnswerComponent implements OnInit {
  addAnswerForm: FormGroup;
  qid: number;
  username: string;
  selectedImg: File;

  constructor(
    private http: HttpClient,
    private api: ApiService,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.addAnswerForm = new FormGroup({
      answer: new FormControl(null),
    });
    this.qid = this.api.getQid();
    this.username = this.api.getUserName();
    console.log(this.username);
    if (this.username === undefined) {
      alert("Login is required");
      this.router.navigate(["/login"]);
    }
  }
  public onFileChanged(event) {
   
    this.selectedImg = event.target.files[0];
  }
  addAnswer() {
    const requestBody: FormData = new FormData();
    requestBody.append("answer", this.addAnswerForm?.value.answer);
    requestBody.append("username", this.username);
    requestBody.append("image", this.selectedImg);
    this.http
      .post<any>("http://localhost:9090/answers/add/" + this.qid, requestBody)
      .subscribe(
        (res) => {
          alert("Your answer is posted for approval");
          this.addAnswerForm?.reset();
          this.router.navigate(["dashboard"]);
        },
        (err) => {
          alert("Something went wrong");
          this.addAnswerForm?.reset();
        }
      );
  }
}
