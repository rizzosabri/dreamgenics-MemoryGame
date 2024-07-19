import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CardComponent } from "./card/card.component";
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-card-panel',
  standalone: true,
  imports: [CardComponent, CommonModule],
  templateUrl: './card-panel.component.html',
  styleUrl: './card-panel.component.css'
})
export class CardPanelComponent {

  @Input() cards: any[] = [];
  @Input() imgBackSide: string = "";
  @Output() endGameEvent = new EventEmitter<number>();

  cardBackImage: string = '';
  lastCard: CardComponent | null = null;
  matchs: number = 0;
  moves: number = 0;
  secs: number = 0;
  timer: number = 0;
  intervalId: any;

  constructor() {
    this.loadCards()
  }

  ngOnInit() {
    this.startTimer();
  }

  ngOnDestroy() {
    clearInterval(this.intervalId);
  }

  async loadCards() {

    this.imgBackSide = await this.getCardBackSide();

    const originalCards = [
      { id: 1, image: "assets/images/eo.png" },
      { id: 2, image: "assets/images/gr.png" },
      { id: 3, image: "assets/images/linfo.png" },
      { id: 4, image: "assets/images/neutrofilo.png" },
      { id: 5, image: "assets/images/plaquetas.png" },
    ];

    // Duplicar las cartas
    const duplicatedCards = [...originalCards, ...originalCards];

    // Mezclar las cartas
    this.cards = this.shuffleArray(duplicatedCards);
  }

  // Algoritmo de mezcla de Fisher-Yates
  shuffleArray(array: any[]): any[] {

    let currentIndex = array.length, randomIndex;

    // Mientras queden elementos a mezclar...
    while (currentIndex !== 0) {
      // Seleccionar un elemento sin mezclar...
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;
      // E intercambiarlo con el elemento actual.
      [array[currentIndex], array[randomIndex]] = [array[randomIndex], array[currentIndex]];
    }
    return array;
  }

  startTimer() {
    this.intervalId = setInterval(() => {
      this.timer++;
    }, 1000);
  }


// LÓGICA DEL JUEGO

  clickCard(card: CardComponent) {
    
    this.moves += 1;
  
      if (this.lastCard === null) {
          this.lastCard = card;
      } else {
          this.handleCardMatch(card);
      }
  }
  
  handleCardMatch(card: CardComponent) {
    if (this.lastCard && card.image === this.lastCard.image) {
        this.matchs += 1;
        this.checkForEndGame();
        this.lastCard = null;
    } else {
        this.resetCards(card);
    }
  }
  
  checkForEndGame() {
      if (this.matchs >= 5) {
          this.saveGameBD();
          setTimeout(()=>this.endGameEvent.emit(this.matchs),1000);
      }
  }
  
  resetCards(card: CardComponent) {
      setTimeout(() => {
          card.toggleFlip();
  
          if (this.lastCard) {
              this.lastCard.toggleFlip();
          }
  
          this.lastCard = null;
      }, 1000);
  }


//LLAMADOS A LA API

async fetchData(url: string, config: RequestInit) {
  try {
      let response = await fetch(url, config);
      if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response;
  } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
  }
}

async getCardBackSide() {
  const url = 'http://localhost:8080/api/imgBackSide?query=dna,%20deoxyribonucleic%20acid,%20health';
  const config: RequestInit = {
      method: "GET",
      headers: {
          "Content-Type": "application/json"
      }
  };

  let response = await this.fetchData(url, config);
  let imageUrl = await response.text();
  return imageUrl;
}

async saveGameBD() {
  const name = localStorage.getItem('name');
  const team = localStorage.getItem('team');
  const moves = this.moves;
  const durationSecs = this.timer;

  const gameData = {
      name,
      team,
      moves,
      durationSecs
  };

  const url = 'http://localhost:8080/api/score';
  const config: RequestInit = {
      method: "POST",
      body: JSON.stringify(gameData),
      headers: {
          "Content-Type": "application/json"
      }
  };

  await this.fetchData(url, config);
}
}