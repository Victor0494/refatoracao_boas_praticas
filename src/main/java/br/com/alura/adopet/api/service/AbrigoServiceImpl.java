package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.entities.Abrigo;
import br.com.alura.adopet.api.entities.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbrigoServiceImpl {

    private final AbrigoRepository abrigoRepository;

    public AbrigoServiceImpl(AbrigoRepository repository) {
        this.abrigoRepository = repository;
    }

    public void cadastrar(CadastrarAbrigoDTO abrigoDTO) {
        if (abrigoRepository.existsByNomeAndTelefoneAndEmail(abrigoDTO.nome(), abrigoDTO.telefone(), abrigoDTO.email())) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        abrigoRepository.save(new Abrigo(abrigoDTO.nome(), abrigoDTO.email(), abrigoDTO.telefone()));
    }

    public List<AbrigoDTO> listarAbrigos() {
        List<Abrigo> abrigos = abrigoRepository.findAll();
        return abrigos.stream()
                .map(AbrigoDTO::new)
                .collect(Collectors.toList());
    }

    public List<PetDTO> listarPetsDoAbrigoPorIdOuNome(String idOuNome) {
        Abrigo abrigo = getAbrigo(idOuNome);

        return abrigo.getPets().stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
    }

    public void cadastrarPet(String idOuNome, PetDTO petDTO) {
        Abrigo abrigo = getAbrigo(idOuNome);
        Pet pet = new Pet();
        pet.setPetRegister(petDTO, abrigo);
        abrigo.adicionarPet(pet);
        abrigoRepository.save(abrigo);
    }

    private Abrigo getAbrigo(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            return abrigoRepository.getReferenceById(id);
        } catch (EntityNotFoundException | NumberFormatException exception) {
            return abrigoRepository.findByNome(idOuNome);
        }
    }
}
