import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { ProjectFormComponent } from './pages/projects/project-form/project-form.component';
import { LoginComponent } from './pages/login/login.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ProjectFormComponent, LoginComponent],
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'angular-app';
  currentPath: string;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.currentPath = window.location.pathname;
      // Adicionar listener para mudanças de URL
      window.addEventListener('popstate', () => {
        this.currentPath = window.location.pathname;
      });
    } else {
      this.currentPath = '/';
    }
  }

  navigate(event: Event, path: string) {
    event.preventDefault();
    if (isPlatformBrowser(this.platformId)) {
      console.log('Navegando para:', path); // Log para depuração
      window.history.pushState({}, '', path);
    }
    this.currentPath = path;
  }
}