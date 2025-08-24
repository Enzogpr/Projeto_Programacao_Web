package com.lucasmartins.projetoweb.model.repository;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TurmaRepository {

    final List<TurmaDto> turmas = new ArrayList<>();

    public TurmaDto save(TurmaDto turmaDto) {
        if (turmaDto.getNome() == null || turmaDto.getNome().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O nome da turma é obrigatório"
            );
        }
        if (turmaDto.getPeriodo() == null || turmaDto.getPeriodo().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O período da turma é obrigatório"
            );
        }
        if (turmaDto.getCurso() == null || turmaDto.getCurso().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O curso da turma é obrigatório"
            );
        }

        turmaDto.setId(turmas.size() + 1);
        turmas.add(turmaDto);
        return turmaDto;
    }

    public TurmaDto findById(int turmaId) {
        return turmas.stream()
                .filter(t -> t.getId() == turmaId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Turma não encontrada com o ID: " + turmaId
                ));
    }

    public List<TurmaDto> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(turmas));
    }

    public TurmaDto findByName(String nome) {
        return turmas.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Turma não encontrada com o Nome: " + nome
                ));
    }

    public List<TurmaDto> findAllByPeriodo(String periodo) {
        List<TurmaDto> turmasEncontradas = turmas.stream()
                .filter(t -> t.getPeriodo().equalsIgnoreCase(periodo.trim()))
                .toList();

        if (turmasEncontradas.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Nenhuma turma encontrada no Período: " + periodo
            );
        }
        return turmasEncontradas;
    }

    public List<TurmaDto> findAllByCurso(String curso) {
        List<TurmaDto> turmasEncontradas = turmas.stream()
                .filter(t -> t.getCurso().equalsIgnoreCase(curso.trim()))
                .toList();

        if (turmasEncontradas.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Nenhuma turma encontrada no Curso: " + curso
            );
        }
        return turmasEncontradas;
    }

    public void deleteById(int turmaId) {
        TurmaDto turmaParaRemover = turmas.stream()
                .filter(t -> t.getId() == turmaId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Turma não encontrada com o ID: " + turmaId
                ));

        turmas.remove(turmaParaRemover);
    }
}
