package com.lucasmartins.projetoweb.controller;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;
import com.lucasmartins.projetoweb.service.TurmaService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TurmaDto cadastrarTurma(@RequestBody TurmaDto turmaDto) {
        return turmaService.cadastrarTurma(turmaDto);
    }

    @GetMapping("/id/{turmaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarTurma(@PathVariable int turmaId) {
        return turmaService.findById(turmaId);
    }

    @GetMapping
public ResponseEntity<?> buscarTodasTurmas() {
    List<TurmaDto> turmas = turmaService.findAll();

    if (turmas.isEmpty()) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Nenhuma turma encontrada");
    }
    return ResponseEntity.ok(turmas);
}

 @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarNome(@PathVariable String nome) {
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

@DeleteMapping("/id/{turmaId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public ResponseEntity<String> deletarTurma(@PathVariable int turmaId) {
    turmaService.deleteById(turmaId);
    return ResponseEntity.ok("Turma " + turmaId + " removida");
}

}
