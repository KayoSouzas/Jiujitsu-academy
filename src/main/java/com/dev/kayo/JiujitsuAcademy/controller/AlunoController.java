package com.dev.kayo.JiujitsuAcademy.controller;


import com.dev.kayo.JiujitsuAcademy.doc.AlunoControllerDocs;
import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.mapper.AlunoMapper;
import com.dev.kayo.JiujitsuAcademy.repository.AlunoRepository;
import com.dev.kayo.JiujitsuAcademy.request.AlunoRequest;
import com.dev.kayo.JiujitsuAcademy.response.AlunoResponse;
import com.dev.kayo.JiujitsuAcademy.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController implements AlunoControllerDocs {

    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;

    public AlunoController(AlunoRepository alunoRepository, AlunoService alunoService) {
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;
    }

    @GetMapping()
    public ResponseEntity<List<AlunoResponse>> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoResponse> getAlunos = alunos.stream().map(AlunoMapper::toAlunoResponse).toList();
        return ResponseEntity.ok(getAlunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlunoResponse>> findById(@PathVariable Long id) {

        Optional<Aluno> optAluno = alunoRepository.findById(id);
        if (optAluno.isPresent()) {
            Optional<AlunoResponse> getAluno = optAluno.map(AlunoMapper::toAlunoResponse);
            return ResponseEntity.ok(getAluno);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());


    }

    @PostMapping("/save")
    public ResponseEntity<AlunoResponse> save(@Valid @RequestBody AlunoRequest request) {
        Aluno newAluno = AlunoMapper.toAluno(request);
        Aluno savedAluno = alunoRepository.save(newAluno);

        return ResponseEntity.status(HttpStatus.CREATED).body(AlunoMapper.toAlunoResponse(savedAluno));
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable Long Id, @Valid @RequestBody AlunoRequest request) {

        try {
            Aluno newAluno = AlunoMapper.toAluno(request);
            newAluno.setId(Id);
            Aluno savedAluno = alunoRepository.save(newAluno);

            return ResponseEntity.ok(AlunoMapper.toAlunoResponse(savedAluno));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Aluno> optAluno = alunoRepository.findById(id);
        if (optAluno.isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }


}