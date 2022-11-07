import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ɵpublishDefaultGlobalUtils } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  username: string;
  userid: number;
  msg: any= [];
  data: any;
  chatForm : FormGroup;

  constructor(
    private http: HttpClient,
    private api: ApiService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.chatForm = new FormGroup({
      message: new FormControl(null)
     
    });

    this.username = this.api.getUserName();
    this.userid = this.api.getuserid();
    if (this.userid == undefined) {
      alert("Login is required");
      this.router.navigate(["/login"]);
    }

    this.getMsg();
  }
  send() {
    let requestBody=this.chatForm?.value;
    requestBody["username"]=this.username;
   
    this.http.post<any>("http://localhost:9092/chat/post", requestBody).subscribe((res) => {
    console.log(res);
    this.chatForm?.reset();
    this.getMsg();
   

    }, (err) => {
      alert("Something went wrong");
    });

  }
  getMsg() {
    this.http.get<any>("http://localhost:9092/chat/get").subscribe((res) => {
      this.msg = res;
      console.log(res);
      

    }, (err) => {
      alert("Something went wrong");
    });


  }

}
