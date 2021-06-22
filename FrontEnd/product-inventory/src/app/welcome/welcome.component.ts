import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  name = '';
  
  constructor(
    private route:ActivatedRoute,
  ) { }

  ngOnInit() {
    console.log("123");
    this.name = this.route.snapshot.params['name'];
  }

}
