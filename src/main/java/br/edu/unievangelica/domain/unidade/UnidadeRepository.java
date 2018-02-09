package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.hibernate.jpa.criteria.expression.function.LowerFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    public List<Unidade> findBySituacaoIn(SituacaoEnum situacaoEnum);

    public List<Unidade> findUnidadeByNomeLike(String nome);
}
