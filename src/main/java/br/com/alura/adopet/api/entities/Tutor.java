package br.com.alura.adopet.api.entities;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.TutorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "tutor", fetch = LAZY)
    private List<Adocao> adocoes;

    public Tutor(TutorDTO tutorDTO) {
        this.nome = tutorDTO.nome();
        this.telefone = tutorDTO.telefone();
        this.email = tutorDTO.email();
    }

    public void atualizaDados(AtualizacaoTutorDTO atualizacaoTutorDTO) {
        this.nome = atualizacaoTutorDTO.nome();
        this.telefone = atualizacaoTutorDTO.telefone();
        this.email = atualizacaoTutorDTO.email();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

}
