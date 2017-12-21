package br.edu.unievangelica.domain.turno;

import br.edu.unievangelica.domain.cursoTurno.CursoTurno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "turno")
@JsonIgnoreProperties({"cursoTurnos"})
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_id_seq")
    @SequenceGenerator(name = "turno_id_seq", sequenceName = "turno_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "codigo", updatable = false)
    @Getter
    @Setter
    private String codigo;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turno")
    @Getter
    @Setter
    private List<CursoTurno> cursoTurnos;
//
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "turno")
//    @Getter
//    @Setter
//    private List<HorarioAula> horarioAulas;
//
//    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "turnos")
//    @Getter
//    @Setter
//    private List<PlanoPagamento> planoPagamentos;

}
