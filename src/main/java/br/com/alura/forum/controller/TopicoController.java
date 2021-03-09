package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

@RestController
@RequestMapping("/topico")
public class TopicoController {
    
    @GetMapping
    public List<TopicoDto> topicos() {
        var t = new Topico("dúvida'", "duvida xtz", new Curso("spring", "java"));

        return TopicoDto.toTopicoDto(Arrays.asList(t,t,t));
    }
}
