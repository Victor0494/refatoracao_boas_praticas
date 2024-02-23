package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.entities.Adocao;
import br.com.alura.adopet.api.entities.Pet;
import br.com.alura.adopet.api.entities.StatusAdocao;
import br.com.alura.adopet.api.entities.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.RequestAdoptValidation;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {

    private final AdocaoRepository repository;

    private final PetRepository petRepository;

    private final TutorRepository tutorRepository;

    private final EmailService emailService;

    private final List<RequestAdoptValidation> validations;

    public AdocaoService(AdocaoRepository repository, PetRepository petRepository, TutorRepository tutorRepository, EmailService emailService, List<RequestAdoptValidation> validations) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
        this.emailService = emailService;
        this.validations = validations;
    }


    public void solicitar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        Pet pet = petRepository.getReferenceById(solicitacaoAdocaoDTO.petId());
        Tutor tutor = tutorRepository.getReferenceById(solicitacaoAdocaoDTO.tutorId());

        validations.forEach(requestAdoptValidation -> requestAdoptValidation.validar(solicitacaoAdocaoDTO));

        Adocao adocao = new Adocao(tutor, pet, solicitacaoAdocaoDTO.motivo());
        repository.save(adocao);

        emailService.sendEmail(adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDTO aprovacaoAdocaoDTO) {
        Adocao adocao = repository.getReferenceById(aprovacaoAdocaoDTO.adocaoId());
        adocao.changeStatusApproved(StatusAdocao.APROVADO);

        emailService.sendEmail(adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");

    }

    public void reprovar(ReprovacaoAdocaoDTO reprovacaoAdocaoDTO) {
        Adocao adocao = repository.getReferenceById(reprovacaoAdocaoDTO.adocaoId());
        adocao.changeStatusDenied(reprovacaoAdocaoDTO.justificativa());

        emailService.sendEmail(adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());

    }
}
