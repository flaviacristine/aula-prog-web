package br.edu.unievangelica.domain.pais;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pais_id_seq")
    @SequenceGenerator( name = "pais_id_seq", sequenceName = "pais_id_seq", allocationSize = 1)
    @Column(name = "pais_id")
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

}
