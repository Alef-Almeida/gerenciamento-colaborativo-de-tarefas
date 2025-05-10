import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ProjectListComponent } from './pages/projects/project-list/project-list.component';
import { ProjectFormComponent } from './pages/projects/project-form/project-form.component';
import { TaskListComponent } from './pages/tasks/task-list/task-list.component';
import { TaskFormComponent } from './pages/tasks/task-form/task-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'projects', component: ProjectListComponent },
  { path: 'projects/new', component: ProjectFormComponent },
  { path: 'projects/:id/edit', component: ProjectFormComponent },
  { path: 'projects/:id/tasks', component: TaskListComponent },
  { path: 'projects/:projectId/tasks/new', component: TaskFormComponent },
  { path: 'projects/:projectId/tasks/:taskId/edit', component: TaskFormComponent }
];
