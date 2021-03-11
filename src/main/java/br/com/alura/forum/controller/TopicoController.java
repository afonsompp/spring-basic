package br.com.alura.forum.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.AtualizaTopicoDto;
import br.com.alura.forum.controller.dto.DetalhesTopicoDto;
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
    public Page<TopicoDto> topicos(@RequestParam int pagina, @RequestParam int quantidade,
            @RequestParam String ordernacao) {
        Pageable paginacao = PageRequest.of(pagina, quantidade, Direction.DESC, ordernacao);
        return TopicoDto.toTopicoDto(topicoRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDto> topico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (!topico.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var dto = DetalhesTopicoDto.topicoDto(topico.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> AtualizarTopico(@PathVariable Long id,
            @RequestBody @Valid AtualizaTopicoDto topicoDto) {
        var t = topicoRepository.findById(id);
        if (!t.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoDto.atualiza(id, topicoRepository);
        return ResponseEntity.ok().body(new TopicoDto(topico));
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrarTopico(@Valid @RequestBody TopicoDtoResponse response,
            UriComponentsBuilder uriBuilder) {
        var topico = topicoRepository.save(response.asTopico(cursoRepository));

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicoDto> deletar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (!topico.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
