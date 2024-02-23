package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.entities.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findByAdotadoFalse();
        return pets.stream().map(PetDTO::new).collect(Collectors.toList());
    }
}
