package br.com.alura.forum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CursoRepositoryTest {
    
    private final CursoRepository repository;

    @Autowired
    public CursoRepositoryTest(CursoRepository repository) {
        this.repository = repository;
    }


    @Test
    public void ShouldReturnCursoByName() {
        var nome = "HTML 5";
        var curso = repository.findByNome(nome);

        assertNotNull(curso);

        assertEquals(curso.getNome(), nome);
    }
}
