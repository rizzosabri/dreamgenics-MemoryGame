import { Component, Output, EventEmitter } from '@angular/core';
import { ScoreTableComponent } from "../score-table/score-table.component";

@Component({
  selector: 'app-home-screen',
  standalone: true,
  imports: [ScoreTableComponent],
  templateUrl: './home-screen.component.html',
  styleUrl: './home-screen.component.css'
})
export class HomeScreenComponent {

  @Output() startGameEvent = new EventEmitter<void>();

  triggerStartGame() {
    
    const nombre = (document.querySelector('input[name="nombre"]') as HTMLInputElement).value;
    const equipo = (document.querySelector('input[name="equipo"]') as HTMLInputElement).value;

    if(nombre=="" || equipo ==""){
       alert("Debe ingresar Nombre y Equipo");
       return;
    }

    this.startGameEvent.emit();

    // Guardar en localStorage
    localStorage.setItem('name', nombre);
    localStorage.setItem('team', equipo);

    console.log('Juego iniciado con:', nombre, equipo);
  
  }
}
