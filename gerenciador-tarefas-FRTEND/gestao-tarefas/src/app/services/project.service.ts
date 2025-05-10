// src/app/services/project.service.ts
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root', // Disponível em toda a aplicação
})
export class ProjectService {
  private apiUrl = 'http://localhost:8080/api/projetos'; 

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  
  createProject(projectData: { title: string; description: string; deliveryDate: string; status: string }) {
    console.log('Tentando criar projeto com dados:', projectData);
    if (isPlatformBrowser(this.platformId)) {
      return fetch(this.apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(projectData),
      })
        .then(response => {
          console.log('Resposta recebida:', response.status, response.statusText);
          if (!response.ok) {
            throw new Error(`Erro: ${response.status} - ${response.statusText}`);
          }
          return response.json();
        })
        .then(data => {
          console.log('Dados retornados:', data);
          return data;
        })
        .catch(error => {
          console.error('Erro na requisição:', error);
          throw error;
        });
    }
    return Promise.reject(new Error('Não está no ambiente do navegador'));
  }

  updateProject(projectId: string, projectData: { title: string; description: string; deliveryDate: string; status: string }) {
    if (isPlatformBrowser(this.platformId)) {
      return fetch(`${this.apiUrl}/${projectId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(projectData),
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Erro ao atualizar projeto');
          }
          return response.json();
        })
        .then(data => data)
        .catch(error => {
          console.error('Erro na requisição:', error);
          throw error;
        });
    }
    return Promise.reject(new Error('Não está no ambiente do navegador'));
  }

  getProjects() {
    if (isPlatformBrowser(this.platformId)) {
      return fetch(this.apiUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Erro ao buscar projetos');
          }
          return response.json();
        })
        .then(data => data)
        .catch(error => {
          console.error('Erro na requisição:', error);
          throw error;
        });
    }
    return Promise.reject(new Error('Não está no ambiente do navegador'));
  }
}