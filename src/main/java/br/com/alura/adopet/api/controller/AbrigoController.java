package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AbrigoServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private final AbrigoServiceImpl abrigoService;

    public AbrigoController(AbrigoServiceImpl abrigoService) {
        this.abrigoService = abrigoService;
    }


    @GetMapping
    public ResponseEntity<List<AbrigoDTO>> listar() {
        return ResponseEntity.ok().body(abrigoService.listarAbrigos());
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarAbrigoDTO abrigo) {
        try {
            abrigoService.cadastrar(abrigo);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<PetDTO>> listarPet(@PathVariable String idOuNome) {
        try {
            return ResponseEntity.ok().body(abrigoService.listarPetsDoAbrigoPorIdOuNome(idOuNome));
        } catch (EntityNotFoundException | NumberFormatException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @PostMapping("/{idOuNome}/pets")
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid PetDTO petDTO) {
        try {
            abrigoService.cadastrarPet(idOuNome, petDTO);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException | NumberFormatException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
