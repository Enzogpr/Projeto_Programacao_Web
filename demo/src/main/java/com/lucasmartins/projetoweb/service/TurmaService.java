package com.lucasmartins.projetoweb.service;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;
import com.lucasmartins.projetoweb.model.Turma;
import com.lucasmartins.projetoweb.model.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public TurmaDto save(TurmaDto dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome da turma é obrigatório");
        }
        if (dto.getPeriodo() == null || dto.getPeriodo().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O período da turma é obrigatório");
        }
        if (dto.getCurso() == null || dto.getCurso().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O curso da turma é obrigatório");
        }

        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setCurso(dto.getCurso());
        turma.setPeriodo(dto.getPeriodo());

        Turma salva = turmaRepository.save(turma);
        dto.setId(salva.getId());
        return dto;
    }

    public TurmaDto findById(int id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
        return toDto(turma);
    }

    public List<TurmaDto> findAll() {
    List<Turma> turmas = turmaRepository.findAll();
    if (turmas.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma turma cadastrada");
    }
    return turmas.stream().map(this::toDto).toList();
}


    public TurmaDto findByName(String nome) {
        Turma turma = turmaRepository.findByNomeIgnoreCase(nome);
        if (turma == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada com o nome: " + nome);
        }
        return toDto(turma);
    }

    public List<TurmaDto> findAllByPeriodo(String periodo) {
        List<Turma> turmas = turmaRepository.findAllByPeriodoIgnoreCase(periodo);
        if (turmas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma turma encontrada no período: " + periodo);
        }
        return turmas.stream().map(this::toDto).toList();
    }

    public List<TurmaDto> findAllByCurso(String curso) {
        List<Turma> turmas = turmaRepository.findAllByCursoIgnoreCase(curso);
        if (turmas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma turma encontrada no curso: " + curso);
        }
        return turmas.stream().map(this::toDto).toList();
    }

    public void deleteById(int id) {
        if (!turmaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada");
        }
        turmaRepository.deleteById(id);
    }

    private TurmaDto toDto(Turma turma) {
        TurmaDto dto = new TurmaDto();
        dto.setId(turma.getId());
        dto.setNome(turma.getNome());
        dto.setCurso(turma.getCurso());
        dto.setPeriodo(turma.getPeriodo());
        return dto;
    }
}
