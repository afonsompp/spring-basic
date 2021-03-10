package br.com.alura.forum.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizaTopicoDto {
    @NotBlank
    @Size(min = 5, max = 150)
    private String titulo;
    @NotBlank
    @Size(min = 5, max = 150)
    private String mensagem;

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


    public Topico atualiza(Long id, TopicoRepository topicoRepository) {
        var topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        
        return topico;
    }

}
