package com.lucasmartins.projetoweb.model.repository;

import com.lucasmartins.projetoweb.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    Turma findByNomeIgnoreCase(String nome);
    List<Turma> findAllByPeriodoIgnoreCase(String periodo);
    List<Turma> findAllByCursoIgnoreCase(String curso);
}
