package br.edu.unievangelica.domain.endereco;

import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.instituicaoEscolaridade.InstituicaoEscolaridade;
import br.edu.unievangelica.domain.mantenedora.Mantenedora;
import br.edu.unievangelica.domain.municipio.Municipio;
import br.edu.unievangelica.domain.unidade.Unidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
@JsonIgnoreProperties({"instituicao", "unidade", "mantenedora", "instituicaoEscolaridade"})
public class Endereco implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
    @SequenceGenerator(name = "endereco_id_seq", sequenceName = "endereco_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @Size(max = 80)
    @Column(name = "logradouro")
    @Getter
    @Setter
    private String logradouro;

    @Size(max = 10)
    @Column(name = "numero")
    @Getter
    @Setter
    private String numero;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "bairro")
    @Getter
    @Setter
    private String bairro;

    @Size(max = 20)
    @Column(name = "caixa_postal")
    @Getter
    @Setter
    private String caixaPostal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Municipio municipio;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "endereco")
    @Getter
    @Setter
    private Instituicao instituicao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "endereco")
    @Getter
    @Setter
    private Unidade unidade;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "endereco")
    @Getter
    @Setter
    private Mantenedora mantenedora;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "endereco")
    @Getter
    @Setter
    private InstituicaoEscolaridade instituicaoEscolaridade;

}
