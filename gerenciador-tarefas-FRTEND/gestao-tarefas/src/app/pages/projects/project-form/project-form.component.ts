import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ProjectService } from '../../../services/project.service';

@Component({
  selector: 'app-project-form',
  standalone: true,
  imports: [],
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.scss'],
})
export class ProjectFormComponent implements OnInit {
  @ViewChild('titleRef') titleRef!: ElementRef<HTMLInputElement>;
  @ViewChild('descriptionRef') descriptionRef!: ElementRef<HTMLTextAreaElement>;
  @ViewChild('deliveryDateRef') deliveryDateRef!: ElementRef<HTMLInputElement>;
  @ViewChild('statusRef') statusRef!: ElementRef<HTMLSelectElement>;

  isEditMode = false;
  mensagemSucesso: string = '';
  mensagemErro: string = '';

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.log('Método onSubmit() foi chamado!');

    const formData = {
      title: this.titleRef.nativeElement.value,
      description: this.descriptionRef.nativeElement.value,
      deliveryDate: this.deliveryDateRef.nativeElement.value,
      status: this.statusRef.nativeElement.value,
    };

    this.log('Dados do formulário:', formData);

    if (!formData.title || !formData.deliveryDate) {
      this.log('Campos obrigatórios não preenchidos');
      this.mensagemErro = 'Por favor, preencha todos os campos obrigatórios.';
      return;
    }

    this.mensagemErro = '';
    this.projectService.createProject(formData)
      .then(data => {
        this.log('Sucesso:', data);
        this.mensagemSucesso = 'Projeto criado com sucesso!';
        this.limparFormulario();
      })
      .catch(error => {
        this.log('Erro:', error.message);
        this.mensagemErro = 'Erro ao criar projeto. Por favor, tente novamente.';
        this.mensagemSucesso = '';
      });

    if (this.isEditMode) {
      this.log('Atualizando projeto...');
    } else {
      this.log('Criando novo projeto...');
    }
  }

  limparFormulario(): void {
    this.titleRef.nativeElement.value = '';
    this.descriptionRef.nativeElement.value = '';
    this.deliveryDateRef.nativeElement.value = '';
    this.statusRef.nativeElement.value = 'PENDING';
  }

  private log(...args: any[]): void {
    console.log(...args);
    // Salvar logs no localStorage para persistência
    const logs = JSON.parse(localStorage.getItem('appLogs') || '[]');
    logs.push({ timestamp: new Date().toISOString(), message: args });
    localStorage.setItem('appLogs', JSON.stringify(logs));
  }
}