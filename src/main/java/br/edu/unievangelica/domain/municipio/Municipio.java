package br.edu.unievangelica.domain.municipio;

import br.edu.unievangelica.domain.endereco.Endereco;
import br.edu.unievangelica.domain.provincia.Provincia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "municipio")
@JsonIgnoreProperties({"provincia"})
public class Municipio implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_id_seq")
    @SequenceGenerator(name = "municipio_id_seq", sequenceName = "municipio_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @Size(max = 5)
    @Column(name = "sigla")
    @Getter
    @Setter
    private String sigla;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Provincia provincia;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
    @Getter
    @Setter
    private List<Endereco> enderecos;

}
