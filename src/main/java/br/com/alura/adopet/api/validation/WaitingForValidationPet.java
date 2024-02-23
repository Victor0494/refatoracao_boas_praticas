package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.entities.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class WaitingForValidationPet implements RequestAdoptValidation {

    private final AdocaoRepository repository;


    public WaitingForValidationPet(AdocaoRepository repository) {
        this.repository = repository;
    }

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        if (repository.existByPetIdAndStatus(solicitacaoAdocaoDTO.petId(), StatusAdocao.AGUARDANDO_AVALIACAO)) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
