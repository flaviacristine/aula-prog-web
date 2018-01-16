package br.edu.unievangelica.domain.mantenedora;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.pais.Pais;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tauan.camargo on 19/12/2017.
 */
@Entity
@Table(name = "mantenedora")
@JsonIgnoreProperties({"instituicoes"})
public class Mantenedora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mantenedora_id_seq")
    @SequenceGenerator(name = "mantenedora_id_seq", sequenceName = "mantenedora_id_seq", allocationSize = 1)
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

    @Size(max = 100)
    @Column(name = "endereco")
    @Getter
    @Setter
    private String endereco;

    @NotNull
    @Size(max = 50)
    @Column(name = "bairro")
    @Getter
    @Setter
    private String bairro;

    @Size(max = 5)
    @Column(name = "numero")
    @Getter
    @Setter
    private String numero;

    @Size(max = 10)
    @Column(name = "caixaPostal")
    @Getter
    @Setter
    private String caixaPostal;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Pais pais;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mantenedora")
    @Getter
    @Setter
    private List<Instituicao> instituicoes;

}
