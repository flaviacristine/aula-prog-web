package br.edu.unievangelica.domain.instituicaoEscolaridade;

import br.edu.unievangelica.domain.pais.Pais;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "instituicao_escolaridade")
public class InstituicaoEscolaridade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instituicao_escolaridade_id_seq")
    @SequenceGenerator(name = "instituicao_escolaridade_id_seq", sequenceName = "instituicao_escolaridade_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 80)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

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

}
