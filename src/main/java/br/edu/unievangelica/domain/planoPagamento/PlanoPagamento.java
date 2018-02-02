package br.edu.unievangelica.domain.planoPagamento;

import br.edu.unievangelica.core.enums.MesEnum;
import br.edu.unievangelica.domain.cursoTurno.CursoTurno;
import br.edu.unievangelica.domain.turno.Turno;
import br.edu.unievangelica.domain.unidade.Unidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plano_pagamento")
public class PlanoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "plano_pagamento_id_seq")
    @SequenceGenerator( name = "plano_pagamento_id_seq", sequenceName = "plano_pagamento_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "nome")
    @Getter @Setter
    private String nome;

    @NotEmpty
    @Column(name = "mes_inicio")
    @Getter @Setter
    private String mesInicio;

    @NotNull
    @Max(99)
    @Min(1)
    @Column(name = "numero_parcelas")
    @Getter @Setter
    private Integer numeroParcelas;

    @Column(name = "dia_vencimento")
    @Getter @Setter
    private Integer diaVencimento = null;

    @NotNull
    @Column(name = "inicio_vigencia")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Getter @Setter
    private Date inicioVigencia;

    @NotNull
    @Column(name = "fim_vigencia")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Getter @Setter
    private Date fimVigencia;

    @Column(name = "ultimo_dia")
    @Getter @Setter
    private Boolean ultimoDia = false;

    @NotNull
    @Column(name = "quantidade_dias_vencimento")
    @Getter @Setter
    private Integer quantidadeDiasVencimento;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    @Getter @Setter
    private Unidade unidade;

    @JoinTable(name = "curso_turno_plano_pagamento", joinColumns = {
            @JoinColumn(name = "curso_turno_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "plano_pagamento_id", referencedColumnName = "id")})
    @ManyToMany
    @Getter @Setter
    private List<CursoTurno> cursoTurnos;

    @JoinTable(name = "plano_pagamento_turno", joinColumns = {
            @JoinColumn(name = "plano_pagamento_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "turno_id", referencedColumnName = "id")})
    @ManyToMany
    @Getter @Setter
    private List<Turno> turnos;

//    @JsonIgnore
//    @JoinTable(name = "grade_curricular_plano_pagamento", joinColumns = {
//            @JoinColumn(name = "grade_curricular_id", referencedColumnName = "id")}, inverseJoinColumns = {
//            @JoinColumn(name = "plano_pagamento_id", referencedColumnName = "id")})
//    @ManyToMany
//    private List<GradeCurricular> gradeCurriculares;
//
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planoPagamento")
//    private List<Divida> dividas;
}
