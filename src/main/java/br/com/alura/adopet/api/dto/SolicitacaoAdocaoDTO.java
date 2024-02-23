package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoAdocaoDTO(
        @NotNull
        Long petId,
        @NotNull
        Long tutorId,
        @NotBlank
        String motivo) {
}

