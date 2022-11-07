import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  searchForm:FormGroup;
  searchString:string;

  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private router: Router

  ) { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      searchStr: new FormControl(null),
    
    });
  }

  search(){
    this.api.setSearchString(this.searchForm.value.searchStr);
    this.router.navigate(["search"]);
    this.searchForm?.reset();

  }
  logout(){
    this.api.logout();
    this.router.navigate(['login']);

  }

}
