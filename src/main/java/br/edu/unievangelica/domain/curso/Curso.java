package br.edu.unievangelica.domain.curso;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.cursoTurno.CursoTurno;
import br.edu.unievangelica.domain.Unidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "curso")
@JsonIgnoreProperties({ "cursoTurnos" })
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_id_seq")
    @SequenceGenerator(name = "curso_id_seq", sequenceName = "curso_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "situacao")
    @Getter @Setter
    private SituacaoEnum situacao = SituacaoEnum.ATIVO;

    @NotNull
    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", orphanRemoval=true)
    @Getter
    @Setter
    private List<CursoTurno> cursoTurnos;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    @Getter
    @Setter
    Unidade unidade;

}
