import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  username:string;

  constructor(
   private api: ApiService
  ) { }

  ngOnInit(): void {
    this.username = this.api.getUserName();
  }

}
