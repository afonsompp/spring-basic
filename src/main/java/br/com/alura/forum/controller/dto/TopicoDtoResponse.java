package br.com.alura.forum.controller.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoDtoResponse {

    @NotBlank @Size(min = 5, max = 150)
    private String titulo;
    @NotBlank @Size(min = 5, max = 150)
    private String mensagem;
    @NotBlank @Size(min = 5, max = 150)
    private String nomeDoCurso;

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeDoCurso() {
        return this.nomeDoCurso;
    }

    public void setNomeDoCurso(String nomeDoCurso) {
        this.nomeDoCurso = nomeDoCurso;
    }

    public Topico asTopico(CursoRepository cursoRepository) {

        var curso = cursoRepository.findByNome(nomeDoCurso);
        return new Topico(titulo, mensagem, curso);
    }
    
}
