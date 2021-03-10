package br.com.alura.forum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.TopicoDtoResponse;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    private final CursoRepository cursoRepository;
    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<TopicoDto> topicos(String nome) {
        if (nome != null) {
            return TopicoDto.toTopicoDto(topicoRepository.findByCursoNome(nome));
        } else {
            return TopicoDto.toTopicoDto(topicoRepository.findAll());
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrarTopico(@Valid @RequestBody TopicoDtoResponse response,
            UriComponentsBuilder uriBuilder) {
        var topico = topicoRepository.save(response.asTopico(cursoRepository));

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
}
