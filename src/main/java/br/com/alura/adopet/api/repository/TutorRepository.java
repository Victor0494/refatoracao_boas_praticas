package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByEmailAndTelefone(String email, String telefone);

}
