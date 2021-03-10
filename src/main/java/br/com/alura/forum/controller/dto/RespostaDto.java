package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Resposta;

public class RespostaDto {
    private Long id;
    private String mensagem;
    private String nomeDoAutor;
    private LocalDateTime dataCriacao;

    public RespostaDto(Long id, String mensagem, String nomeDoAutor, LocalDateTime dataCriacao) {
        this.id = id;
        this.mensagem = mensagem;
        this.nomeDoAutor = nomeDoAutor;
        this.dataCriacao = dataCriacao;
    }

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.nomeDoAutor = resposta.getAutor().getNome();
        this.dataCriacao = resposta.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeDoAutor() {
        return nomeDoAutor;
    }

    public void setNomeDoAutor(String nomeDoAutor) {
        this.nomeDoAutor = nomeDoAutor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
