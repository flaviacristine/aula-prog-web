package br.edu.unievangelica.domain.unidade;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "unidade")
public class Unidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "unidade_id_seq")
    @SequenceGenerator( name = "unidade_id_seq", sequenceName = "unidade_id_seq", allocationSize = 1)
    @Column(name = "unidade_id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "codigo", updatable= false)
    @Getter @Setter
    private String codigo;

    @NotEmpty
    @Size(max = 80)
    @Column(name = "nome")
    @Getter @Setter
    private String nome;

//    @Enumerated(EnumType.ORDINAL)
//    @Column(name = "situacao")
//    @Getter @Setter
//    private SituacaoEnum situacao = SituacaoEnum.ATIVO;
//
//    @Valid
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
//    @Getter @Setter
//    private Endereco endereco;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "instituicao_id", referencedColumnName = "id")
//    @Getter @Setter
//    private Instituicao instituicao;
//
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
//    @JoinColumn(name = "arquivo_id", referencedColumnName = "id")
//    @Getter @Setter
//    private Arquivo arquivo;

}
