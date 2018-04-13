package br.edu.unievangelica.domain.Produto;

import br.edu.unievangelica.domain.provincia.Provincia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//import br.edu.unievangelica.domain.provincia.Provincia;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.validator.constraints.NotEmpty;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.util.List;
@Entity
@Table(name = "produto")
public class Produto implements Serializable {




    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
    @SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "marca")
    @Getter
    @Setter
    private String marca;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "descricao")
    @Getter
    @Setter
    private String descricao;

    @NotEmpty
    @Column(name = "preco")
    @Getter
    @Setter
    private float preco;


}


