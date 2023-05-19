import { Component } from '@angular/core';

@Component({
  selector: 'app-root', //nom de la balise associé au composant <app-root></app-root>
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] //style css des composants
})
export class AppComponent {
  title = 'catalog';
}
