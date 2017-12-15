package br.edu.unievangelica.domain.instituicao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "instituicao")
public class Instituicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instituicao_id_seq")
    @SequenceGenerator(name = "instituicao_id_seq", sequenceName = "instituicao_id_seq", allocationSize = 1)
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

    @NotEmpty
    @Size(max = 20)
    @Column(name = "numero_fiscal")
    @Getter
    @Setter
    private String numeroFiscal;

}
