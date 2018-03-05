package br.edu.unievangelica.domain.instituicao;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.endereco.Endereco;
import br.edu.unievangelica.domain.mantenedora.Mantenedora;
import br.edu.unievangelica.domain.pais.Pais;
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
@Table(name = "instituicao")
@JsonIgnoreProperties({"unidades"})
public class Instituicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instituicao_id_seq")
    @SequenceGenerator(name = "instituicao_id_seq", sequenceName = "instituicao_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "codigo", updatable = false)
    @Getter
    @Setter
    private String codigo;

    @NotEmpty
    @Size(max = 80)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "situacao")
    @Getter @Setter
    private SituacaoEnum situacao = SituacaoEnum.ATIVO;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "numero_fiscal")
    @Getter
    @Setter
    private String numeroFiscal;

    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Endereco endereco;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mantenedora_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Mantenedora mantenedora;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicao")
    @Getter
    @Setter
    private List<Unidade> unidades;

}
