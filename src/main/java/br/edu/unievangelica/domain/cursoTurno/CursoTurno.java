package br.edu.unievangelica.domain.cursoTurno;

import br.edu.unievangelica.domain.curso.Curso;
import br.edu.unievangelica.domain.turno.Turno;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "curso_turno")
public class CursoTurno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_turno_id_seq")
    @SequenceGenerator(name = "curso_turno_id_seq", sequenceName = "curso_turno_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Curso curso;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turno_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Turno turno;

    @NotNull
    @Max(999)
    @Min(0)
    @Column(name = "quantidade_vagas")
    @Getter
    @Setter
    private Integer quantidadeVagas;

//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "cursoTurnos")
//    private List<PlanoPagamento> planoPagamentos;
//
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cursoTurno")
//    private List<Aluno> alunos;
//
//    @Valid
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoTurno")
//    private List<GradeCurricular> gradeCurriculares;

}
