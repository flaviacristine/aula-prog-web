package br.edu.unievangelica.domain.estado;

import br.edu.unievangelica.domain.pais.Pais;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_id_seq")
    @SequenceGenerator(name = "estado_id_seq", sequenceName = "estado_id_seq", allocationSize = 1)
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
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @Getter
    @Setter
    Pais pais;

}
