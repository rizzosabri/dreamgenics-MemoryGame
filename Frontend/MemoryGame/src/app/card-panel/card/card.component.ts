import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
  @Input() image: string = '';
  @Input() imgBackSide: string = '';
  @Output() clickCardEvent = new EventEmitter<CardComponent>();

  isFlipped = true;
  isClickable = true;

  constructor (){
    this.showContent() 
    setTimeout(()=>this.hideContent(),1500)
  }

  clickCard(){ 

    if (!this.isFlipped) {
      return; 
    }
  
    this.toggleFlip()
    this.clickCardEvent.emit(this)
    
   }

  turnIsClickable() {
    this.isClickable = true;
  }

  toggleFlip() {
    this.isFlipped = !this.isFlipped; 
  }

  showContent() {
    this.isFlipped = false;
  }

  hideContent() {
    this.isFlipped = true;
  }

}
