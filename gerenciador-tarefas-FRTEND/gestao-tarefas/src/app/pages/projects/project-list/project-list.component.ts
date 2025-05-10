import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss'] // Altere de .css para .scss
})
export class ProjectListComponent {
  projects = [
    { id: 1, name: 'Projeto 1' },
    { id: 2, name: 'Projeto 2' }
  ];
}