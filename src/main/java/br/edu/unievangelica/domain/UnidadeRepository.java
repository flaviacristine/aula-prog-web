package br.edu.unievangelica.domain;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    public List<Unidade> findBySituacaoIn(SituacaoEnum situacaoEnum);

    @Query(value = "SELECT * FROM unidade WHERE lower(nome) = lower(:nome) AND id <> :id", nativeQuery = true)
    public List<Unidade> findUnidadeByNomeLike(@Param("nome") String nome, @Param("id") long id);

    @Query(value = "SELECT * FROM unidade WHERE lower(nome) = lower(:nome)", nativeQuery = true)
    public List<Unidade> findUnidadeByNomeLike(@Param("nome") String nome);
}
