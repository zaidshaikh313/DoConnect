import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

const TOKEN = 'u_token';
const USERNAME = 'u_username';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: HttpClient
  ) { }

  searchString: string;
  username: string;
  qid:number;
  userid:number;

  token: string;
  setUserName(name: string) {
    window.localStorage.removeItem(USERNAME)
    window.localStorage.setItem(USERNAME,name);
  }
  getUserName() {
    return window.localStorage.getItem(USERNAME)
  }


  setToken(importtoken: string) {
    window.localStorage.removeItem(TOKEN)
    window.localStorage.setItem(TOKEN,importtoken);
  }
  getToken() {
    return window.localStorage.getItem(TOKEN);
  }
  setuserid(id: number) {
    this.userid = id;
  }
  getuserid() {
    return this.userid;
  }
  getData() {
    return this.http.get<any>("http://localhost:9090/questions").pipe(
      map((res: any) => {
        return res;
      })
    );
  }
  getApprovedQuestions() {
    return this.http.get<any>("http://localhost:9090/questions/all").pipe(
      map((res: any) => {
        return res;
      })
    );
  }
  getAllAnswers() {
    return this.http.get<any>("http://localhost:9090/answers/all").pipe(
      map((res: any) => {
        return res;
      })
    );
  }
  setSearchString(search:string){
    this.searchString=search;
  }
  getSearchString(){
    return this.searchString;
  }
  setQid(qid:number){
    this.qid=qid;

  }
  getQid(){
    return this.qid;
  }

  logout(){
    window.localStorage.removeItem(USERNAME);
    window.localStorage.removeItem(TOKEN);
  }



}
