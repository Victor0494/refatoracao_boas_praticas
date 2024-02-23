package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.entities.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class MaxLimitAdoptValidation implements RequestAdoptValidation {

    private final AdocaoRepository repository;


    public MaxLimitAdoptValidation(AdocaoRepository repository) {
        this.repository = repository;
    }

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        int contador = 0;
        if (repository.existByTutorIdAndStatus(solicitacaoAdocaoDTO.tutorId(), StatusAdocao.APROVADO)) {
            contador = contador + 1;
        }
        if (contador == 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
