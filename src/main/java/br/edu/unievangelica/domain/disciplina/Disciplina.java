package br.edu.unievangelica.domain.disciplina;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplina_id_seq")
    @SequenceGenerator(name = "disciplina_id_seq", sequenceName = "disciplina_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @Size(max = 100)
    @NotEmpty
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @Size(max = 10)
    @NotEmpty
    @Column(name = "codigo")
    @Getter
    @Setter
    private String codigo;

//    @JsonIgnore
//    @Enumerated(EnumType.ORDINAL)
//    @Column(name = "situacao")
//    @Getter @Setter
//    private SituacaoEnum situacao = SituacaoEnum.ATIVO;

//    @JsonIgnore
//    @NotNull
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
//    @Getter @Setter
//    private Unidade unidade;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplina")
//    @Getter @Setter
//    private List<SerieDisciplina> serieDisciplinas;

}
