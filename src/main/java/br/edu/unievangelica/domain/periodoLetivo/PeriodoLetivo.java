package br.edu.unievangelica.domain.periodoLetivo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "periodo_letivo")
public class PeriodoLetivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "periodo_letivo_id_seq")
    @SequenceGenerator( name = "periodo_letivo_id_seq", sequenceName = "periodo_letivo_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 80)
    @Column(name = "nome")
    @Getter @Setter
    private String nome;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "regime")
    @Getter @Setter
    private String regime;

    @NotNull
    @Column(name = "data_inicial")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Getter @Setter
    private Date dataInicial;

    @NotNull
    @Column(name = "data_final")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat (pattern = "dd/MM/yyyy")
    @Getter @Setter
    private Date dataFinal;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "periodoLetivo")
//    @Getter @Setter
//    private List<Turma> turmas;
//
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoLetivo")
//    @Getter @Setter
//    private List<ValorCurso> valorCursos;
}
