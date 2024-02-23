package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.entities.Pet;
import br.com.alura.adopet.api.entities.TipoPet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDTO(
        @Enumerated(EnumType.STRING)
        @NotNull
        TipoPet tipo,
        @NotBlank
        String nome,
        @NotBlank
        String raca,
        @NotNull
        Integer idade,
        @NotBlank
        String cor,
        @NotNull
        Float peso) {

        public PetDTO(Pet pet){
                this(pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso());
        }
}
