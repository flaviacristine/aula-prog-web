package br.edu.unievangelica.domain.pais;

import br.edu.unievangelica.domain.estado.Estado;
import br.edu.unievangelica.domain.instituicaoEscolaridade.InstituicaoEscolaridade;
import br.edu.unievangelica.domain.mantenedora.Mantenedora;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pais")
@JsonIgnoreProperties({"estados", "mantenedoras", "instituicoesEscolaridade"})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_id_seq")
    @SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @Size(max = 5)
    @Column(name = "sigla")
    @Getter
    @Setter
    private String sigla;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    @Getter
    @Setter
    private List<Estado> estados;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    @Getter
    @Setter
    private List<Mantenedora> mantenedoras;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    @Getter
    @Setter
    private List<InstituicaoEscolaridade> instituicoesEscolaridade ;
}
