import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;
  name: string;


  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
    });
  }
  login() {
 

    let logInRequestBody = this.loginForm?.value;
    this.http.post<any>("http://localhost:9090/signIn", logInRequestBody).subscribe(
      (res) => {



        this.api.setToken(res.token);

        this.api.setUserName(this.loginForm.value.username);
        this.api.setuserid(res.id);
  
        if (res.role === "admin") {
          this.router.navigate(["adminDashboard"]);
        }
        else {
          this.router.navigate(["dashboard"]);
        } this.loginForm?.reset();

      }, (err) => {
        alert("Invalid Credentials");
      }

    );

  }


}
