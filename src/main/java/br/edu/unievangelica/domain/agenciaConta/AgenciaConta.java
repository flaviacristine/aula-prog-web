package br.edu.unievangelica.domain.agenciaConta;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.banco.Banco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "agencia_conta")
public class AgenciaConta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencia_conta_id_seq")
    @SequenceGenerator(name = "agencia_conta_id_seq", sequenceName = "agencia_conta_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @NotEmpty
    @Size(max = 16)
    @Column(name = "conta")
    @Getter
    @Setter
    private String conta;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "agencia")
    @Getter
    @Setter
    private String agencia;

    @NotEmpty
    @Size(max = 35)
    @Column(name = "tipo_conta")
    @Getter
    @Setter
    private String tipoConta;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banco_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Banco banco;

    @NotNull
    @Enumerated
    @Column(name = "situacao")
    @Getter
    @Setter
    private SituacaoEnum situacao = SituacaoEnum.ATIVO;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agenciaConta")
//    private List<DetalhesPagamento> detalhesPagamentos;
}
