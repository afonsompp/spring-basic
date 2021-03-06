package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

    List<Topico> findByCursoNome(String nome);
    
}
