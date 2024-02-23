package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.entities.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class AvailablePetValidation implements RequestAdoptValidation {

    private final PetRepository petRepository;

    public AvailablePetValidation(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        Pet pet = petRepository.getReferenceById(solicitacaoAdocaoDTO.petId());
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
