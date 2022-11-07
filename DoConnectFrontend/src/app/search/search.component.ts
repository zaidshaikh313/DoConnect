import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ApiService } from "../Service/api.service";

@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.css"],
})
export class SearchComponent implements OnInit {
  searchString: string;
  questions: any = [];
  answers: any = [];
  noQuestions: boolean;

  constructor(private api: ApiService, private http: HttpClient) {}

  ngOnInit(): void {
    this.searchString = this.api.getSearchString();
    this.search();
  }
  search() {
    console.log("hii");
    this.http
      .get<any>("http://localhost:9090/questions/" + this.searchString)
      .subscribe((res) => {
        console.log(res);
        if (res.length === 0) {
          this.noQuestions = true;
        }
        for (let i = 0; i < res.length; i++) {
          console.log(res[i].questionDto);
          this.questions.push(res[i].questionDto);
          for (let j = 0; j < res[i].answerDtos.length; j++) {
            console.log(res[i].answerDtos[j]);
            this.answers.push(res[i].answerDtos[j]);
          }
        }
        // console.log(this.questions);

        // console.log(this.answers);
      });
  }
}
