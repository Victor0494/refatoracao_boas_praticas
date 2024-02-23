package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.entities.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class WaitingForValidationTutor implements RequestAdoptValidation {

    private final AdocaoRepository repository;


    public WaitingForValidationTutor(AdocaoRepository repository) {
        this.repository = repository;
    }

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        if (repository.existByTutorIdAndStatus(solicitacaoAdocaoDTO.tutorId(), StatusAdocao.AGUARDANDO_AVALIACAO)) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
