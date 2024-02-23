package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.service.PetServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetServiceImpl petService;

    public PetController(PetServiceImpl petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> listarTodosDisponiveis() {
        return ResponseEntity.ok(petService.getAllPets());
    }

}
