package com.dev.kayo.JiujitsuAcademy.controller;

import com.dev.kayo.JiujitsuAcademy.doc.TurmaControllerDocs;
import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import com.dev.kayo.JiujitsuAcademy.exceptions.ProfessorNãoEncontradoException;
import com.dev.kayo.JiujitsuAcademy.mapper.TurmaMapper;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import com.dev.kayo.JiujitsuAcademy.repository.TurmaRepository;
import com.dev.kayo.JiujitsuAcademy.request.TurmaRequest;
import com.dev.kayo.JiujitsuAcademy.response.TurmaResponse;
import com.dev.kayo.JiujitsuAcademy.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turma")
public class TurmaController implements TurmaControllerDocs {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final TurmaService turmaService;

    public TurmaController(ProfessorRepository professorRepository, TurmaRepository turmaRepository, TurmaService turmaService) {
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.turmaService = turmaService;
    }

    @PostMapping("/save")
    public ResponseEntity<TurmaResponse> save(@Valid @RequestBody TurmaRequest request) {
        Turma saveTurma = turmaService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(TurmaMapper.toTurmaResponse(saveTurma));
    }


    @PostMapping("/{turmaID}/matricular/{alunoID}")
    public ResponseEntity<TurmaResponse> matricular(@PathVariable Long turmaID, @PathVariable Long alunoID){

        Turma turma = turmaService.matricularAluno(turmaID, alunoID);
        return ResponseEntity.status(HttpStatus.CREATED).body(TurmaMapper.toTurmaResponse(turma));
    }




    @GetMapping("")
    public ResponseEntity<List<TurmaResponse>> findAll() {
        List<Turma> turmaList = turmaRepository.findAll();

        return ResponseEntity.ok(TurmaMapper.toResponseList(turmaList));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TurmaResponse>> findById(@PathVariable Long id) {

        Optional<Turma> optTurma = turmaRepository.findById(id);

        if (optTurma.isPresent()) {
            Optional<TurmaResponse> getTurma = optTurma.map(TurmaMapper::toTurmaResponse);

            return ResponseEntity.ok(getTurma);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Optional<Turma> optTurma = turmaRepository.findById(id);
        if (optTurma.isPresent()) {
            turmaRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TurmaResponse> update(@PathVariable Long id, @Valid @RequestBody TurmaRequest request) {

        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        Professor professor = professorRepository.findById(request.professorId()).orElseThrow(()-> new ProfessorNãoEncontradoException("Professor não encontrado."));

        if (optionalTurma.isPresent()) {

            Turma turma = TurmaMapper.toTurma(request);
            turma.setId(id);
            turma.setProfessor(professor);
            turmaRepository.save(turma);

            return ResponseEntity.ok(TurmaMapper.toTurmaResponse(turma));

        }
            return ResponseEntity.notFound().build();

    }

}
