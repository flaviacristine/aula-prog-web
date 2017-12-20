package br.edu.unievangelica.domain.mantenedora;

import br.edu.unievangelica.domain.instituicao.Instituicao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tauan.camargo on 19/12/2017.
 */
@Entity
@Table(name = "mantenedora")
@JsonIgnoreProperties({"mantenedoras"})
public class Mantenedora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "mantenedora_id_seq")
    @SequenceGenerator( name = "mantenedora_id_seq", sequenceName = "mantenedora_id_seq", allocationSize = 1)
    @Column(name = "id")
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

    @NotEmpty
    @Size(max = 20)
    @Column(name = "numero_fiscal")
    @Getter @Setter
    private String numeroFiscal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mantenedora")
    @Getter @Setter
    private List<Instituicao> instituicoes;

}
