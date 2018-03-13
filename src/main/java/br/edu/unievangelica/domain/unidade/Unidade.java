package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.arquivo.Arquivo;
import br.edu.unievangelica.domain.avaliacaoPadrao.AvaliacaoPadrao;
import br.edu.unievangelica.domain.curso.Curso;
import br.edu.unievangelica.domain.disciplina.Disciplina;
import br.edu.unievangelica.domain.emolumento.Emolumento;
import br.edu.unievangelica.domain.endereco.Endereco;
import br.edu.unievangelica.domain.frequenciaPadrao.FrequenciaPadrao;
import br.edu.unievangelica.domain.instituicao.Instituicao;
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
@Table(name = "unidade")
@JsonIgnoreProperties({"disciplinas", "cursos", "avaliacoesPadrao", "frequenciasPadrao", "emolumentos"})
public class Unidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_id_seq")
    @SequenceGenerator(name = "unidade_id_seq", sequenceName = "unidade_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    @Setter
    protected long id;

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
    @Getter
    @Setter
    private SituacaoEnum situacao = SituacaoEnum.ATIVO;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Endereco endereco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unidade")
    @Getter
    @Setter
    private List<Disciplina> disciplinas;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "arquivo_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Arquivo arquivo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unidade")
    @Getter
    @Setter
    private List<Curso> cursos;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instituicao_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Instituicao instituicao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unidade")
    @Getter
    @Setter
    private List<AvaliacaoPadrao> avaliacoesPadrao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unidade")
    @Getter
    @Setter
    private List<FrequenciaPadrao> frequenciasPadrao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unidade")
    @Getter
    @Setter
    private List<Emolumento> emolumentos;
}
