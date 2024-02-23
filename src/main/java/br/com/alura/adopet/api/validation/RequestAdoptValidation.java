package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;

public interface RequestAdoptValidation {

    void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO);
}
