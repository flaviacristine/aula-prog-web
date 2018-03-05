package br.edu.unievangelica.domain.arquivo;

import br.edu.unievangelica.domain.unidade.Unidade;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arquivo_id_seq")
    @SequenceGenerator(name = "arquivo_id_seq", sequenceName = "arquivo_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @Column(name = "dados")
    @Getter @Setter
    private byte[] dados;

    @Column(name = "nome")
    @Getter @Setter
    private String nome;

    @Column(name = "tipo")
    @Getter @Setter
    private String tipo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "arquivo")
    private Unidade unidade;
}
