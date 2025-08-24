package com.lucasmartins.projetoweb.service;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;
import com.lucasmartins.projetoweb.model.repository.TurmaRepository;

import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public TurmaDto cadastrarTurma(TurmaDto turmaDto) {
        return turmaRepository.save(turmaDto);
    }

    public TurmaDto findById(int turmaId) {
        return turmaRepository.findById(turmaId);
    }

      public List<TurmaDto> findAll() {
        return turmaRepository.findAll();
    }

    public TurmaDto findByName(String nome) {
        return turmaRepository.findByName(nome);
    }

     public List<TurmaDto> findAllByPeriodo(String periodo) {
        return turmaRepository.findAllByPeriodo(periodo);
    }

     public List<TurmaDto> findAllByCurso(String curso) {
        return turmaRepository.findAllByCurso(curso);
    }

    public void deleteById(int turmaId) {
    turmaRepository.deleteById(turmaId);
}

}
