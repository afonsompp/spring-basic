package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }
    
    @GetMapping
    public List<TopicoDto> topicos(String nome) {
        if (nome != null) {
            return TopicoDto.toTopicoDto(topicoRepository.findByCursoNome(nome));
        }else{
            return TopicoDto.toTopicoDto(topicoRepository.findAll());
        }
    }
}
