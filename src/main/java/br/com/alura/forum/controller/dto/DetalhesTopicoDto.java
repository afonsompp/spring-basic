package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

public class DetalhesTopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeDoAutor;
    private StatusTopico statusTopico;
    private List<RespostaDto> respostas;

    public DetalhesTopicoDto() {
    }

    public DetalhesTopicoDto(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String nomeDoAutor,
            StatusTopico statusTopico, List<RespostaDto> respostas) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.nomeDoAutor = nomeDoAutor;
        this.statusTopico = statusTopico;
        this.respostas = respostas;
    }

    public DetalhesTopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeDoAutor = topico.getAutor().getNome();
        this.statusTopico = topico.getStatus();
        this.respostas = topico.getRespostas()
                .stream()
                .map(RespostaDto::new)
                .collect(Collectors.toList());
    }

    public String getNomeDoAutor() {
        return nomeDoAutor;
    }

    public void setNomeDoAutor(String nomeDoAutor) {
        this.nomeDoAutor = nomeDoAutor;
    }

    public StatusTopico getStatusTopico() {
        return statusTopico;
    }

    public void setStatusTopico(StatusTopico statusTopico) {
        this.statusTopico = statusTopico;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaDto> respostas) {
        this.respostas = respostas;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public static List<DetalhesTopicoDto> toTopicoDto(List<Topico> topico) {
        return topico.stream().map(DetalhesTopicoDto::new).collect(Collectors.toList());
    }

    public static DetalhesTopicoDto topicoDto(Topico topico) {
        return new DetalhesTopicoDto(topico);
    }

}