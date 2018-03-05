package br.edu.unievangelica.domain.emolumento;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.unidade.Unidade;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "emolumento")
public class Emolumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emolumento_id_seq")
    @SequenceGenerator(name = "emolumento_id_seq", sequenceName = "emolumento_id_seq", allocationSize = 1)
    @Column(name = "id", updatable= false)
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "codigo", updatable= false, unique = true)
    @Getter @Setter
    private String codigo;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome")
    @Getter @Setter
    private String nome;

    @NotNull
    @Column(name = "valor")
    @NumberFormat(pattern = "#,##0.00")
    @Getter @Setter
    private BigDecimal valor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "situacao")
    @Getter @Setter
    private SituacaoEnum situacao = SituacaoEnum.ATIVO;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", updatable= false )
    @Getter @Setter
    Unidade unidade;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emolumento")
//    private List<Divida> dividas;
}
