package br.edu.unievangelica.domain.frequenciaPadrao;

import br.edu.unievangelica.domain.unidade.Unidade;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class FrequenciaPadrao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frequencia_padrao_id_seq")
    @SequenceGenerator(name = "frequencia_padrao_id_seq", sequenceName = "frequencia_padrao_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Unidade unidade;

    @NotEmpty
    @Size(max = 45)
    @Column(name = "descricao")
    @Getter
    @Setter
    private String descricao;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Column(name = "ordem")
    @Getter
    @Setter
    private Integer ordem;

}
