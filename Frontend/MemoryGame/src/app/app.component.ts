import { Component,  ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeScreenComponent } from "./home-screen/home-screen.component";
import { CardPanelComponent } from "./card-panel/card-panel.component";
import { ScoreTableComponent } from "./score-table/score-table.component";
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeScreenComponent, CardPanelComponent, ScoreTableComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'MemoryGame';
  isPlaying = false;
  showEndScreen = false;

  @ViewChild('cardPanel') cardPanel!: CardPanelComponent;

  startGame() {
    this.isPlaying = true;
  }

  endGame(){
    this.showEndScreen = true;
    this.isPlaying = false;
  }

  playAgain(){
    this.isPlaying = false;
    this.showEndScreen = false;
  }
  
}
