package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.entities.Adocao;
import br.com.alura.adopet.api.entities.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean existByPetIdAndStatus(Long petId, StatusAdocao status);

    boolean existByTutorIdAndStatus(Long tutorId, StatusAdocao status);

}
