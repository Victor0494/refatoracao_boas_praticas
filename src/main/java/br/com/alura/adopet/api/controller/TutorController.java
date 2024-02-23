package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.TutorDTO;
import br.com.alura.adopet.api.entities.Tutor;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.TutorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorServiceImpl tutorService;

    public TutorController(TutorServiceImpl tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid TutorDTO tutorDTO) {
        try{
            tutorService.cadastrarTutor(tutorDTO);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizacaoTutorDTO atualizacaoTutorDTO) {
        tutorService.atualizarTutor(atualizacaoTutorDTO);
        return ResponseEntity.ok().build();
    }

}
