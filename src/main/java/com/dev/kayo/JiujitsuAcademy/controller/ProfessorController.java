package com.dev.kayo.JiujitsuAcademy.controller;


import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.mapper.ProfessorMapper;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import com.dev.kayo.JiujitsuAcademy.request.ProfessorRequest;
import com.dev.kayo.JiujitsuAcademy.response.ProfessorResponse;
import com.dev.kayo.JiujitsuAcademy.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorService professorService;
    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorService professorService, ProfessorRepository professorRepository) {
        this.professorService = professorService;
        this.professorRepository = professorRepository;
    }

    @GetMapping()
    public ResponseEntity<List<ProfessorResponse>> findAll() {
        List<Professor> professor = professorService.findAll();
        List<ProfessorResponse> getProfessor = ProfessorMapper.toProfessorResponse(professor).stream().toList();

        return ResponseEntity.ok(getProfessor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProfessorResponse>> findById(@PathVariable Long id) {

        Optional<Professor> optProfessor = professorService.findById(id);

        if (optProfessor.isPresent()) {
            Optional<ProfessorResponse> getProfessor = optProfessor.map(ProfessorMapper::toProfessorResponse);
            return ResponseEntity.ok(getProfessor);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<ProfessorResponse> save(@Valid @RequestBody ProfessorRequest request) {
        Professor newProfessor = ProfessorMapper.toProfessor(request);
        professorService.save(newProfessor);

        return ResponseEntity.status(HttpStatus.CREATED).body(ProfessorMapper.toProfessorResponse(newProfessor));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Professor> professor = professorService.findById(id);
        if (professor.isPresent()) {
            professorService.delete(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessorResponse> update( @PathVariable Long id, @Valid @RequestBody ProfessorRequest request) {

        try {
            Professor professor = ProfessorMapper.toProfessor(request);
            professor.setId(id);
            Professor saveProfessor = professorRepository.save(professor);

            return ResponseEntity.ok(ProfessorMapper.toProfessorResponse(saveProfessor));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
