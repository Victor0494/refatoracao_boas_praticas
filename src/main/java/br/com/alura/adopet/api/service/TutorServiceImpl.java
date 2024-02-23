package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.TutorDTO;
import br.com.alura.adopet.api.entities.Tutor;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorServiceImpl {

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public void cadastrarTutor(TutorDTO tutorDTO) {
        if(tutorRepository.existsByEmailAndTelefone(tutorDTO.email(), tutorDTO.telefone())) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        tutorRepository.save(new Tutor(tutorDTO));
    }

    public void atualizarTutor(AtualizacaoTutorDTO atualizacaoTutorDTO) {
        Tutor tutor = tutorRepository.getReferenceById(atualizacaoTutorDTO.id());
        tutor.atualizaDados(atualizacaoTutorDTO);
    }
}
