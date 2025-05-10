import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.scss']
})
export class TaskListComponent implements OnInit {
  projectId: number | null = null;
  tasks = [
    { id: 1, name: 'Tarefa 1', status: 'Pendente' },
    { id: 2, name: 'Tarefa 2', status: 'Concluída' }
  ];

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.projectId = +this.route.snapshot.paramMap.get('id')!;
  }
}