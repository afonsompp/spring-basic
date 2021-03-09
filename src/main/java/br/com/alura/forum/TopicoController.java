package br.com.alura.forum;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

@Controller
public class TopicoController {
    @RequestMapping("/topicos")
    @ResponseBody
    public List<Topico> topicos() {
        var t = new Topico("dúvida'", "duvida xtz", new Curso("spring", "java"));

        return Arrays.asList(t,t,t);
    }
}
