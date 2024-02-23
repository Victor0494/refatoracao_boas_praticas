package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.entities.Abrigo;

public record AbrigoDTO(String nome, String telefone) {

    public AbrigoDTO(Abrigo abrigo) {
        this(abrigo.getNome(), abrigo.getTelefone());
    }

}
