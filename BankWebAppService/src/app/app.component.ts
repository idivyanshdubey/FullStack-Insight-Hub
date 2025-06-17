import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string;

  constructor() {
    this.title = this.getAppTitle();
  }

  private getAppTitle(): string {
    return 'angularapp';
  }
}