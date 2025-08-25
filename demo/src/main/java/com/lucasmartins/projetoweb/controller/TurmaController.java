package com.lucasmartins.projetoweb.controller;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;
import com.lucasmartins.projetoweb.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TurmaDto criar(@RequestBody TurmaDto dto) {
        return turmaService.save(dto);
    }

    @GetMapping("/{id}")
    public TurmaDto buscarPorId(@PathVariable int id) {
        return turmaService.findById(id);
    }

    @GetMapping
    public List<TurmaDto> listar() {
        return turmaService.findAll();
    }

    @GetMapping("/nome/{nome}")
    public TurmaDto buscarPorNome(@PathVariable String nome) {
        return turmaService.findByName(nome);
    }

    @GetMapping("/periodo/{periodo}")
    public List<TurmaDto> buscarPorPeriodo(@PathVariable String periodo) {
        return turmaService.findAllByPeriodo(periodo);
    }

    @GetMapping("/curso/{curso}")
    public List<TurmaDto> buscarPorCurso(@PathVariable String curso) {
        return turmaService.findAllByCurso(curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable int id) {
        turmaService.deleteById(id);
    }
}
