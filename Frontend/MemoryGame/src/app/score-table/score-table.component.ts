import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-score-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './score-table.component.html',
  styleUrl: './score-table.component.css'
})
export class ScoreTableComponent {

  @Input() scores: any[] = [];

  constructor() {
    this.loadScore()
  }

async loadScore() {

  let url = 'http://localhost:8080/api/score';
      
  let metodoType = "GET"
  let config = {
    method: metodoType,
    headers: {
        "Content-Type": "application/json"
    }
};

let response = await fetch(url,config);

let json = await response.json()

this.scores = json;

}

}
