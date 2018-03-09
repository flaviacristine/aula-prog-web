package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoRepository extends CrudRepository<Banco, Long> {

    public List<Banco> findBySituacaoIn(SituacaoEnum situacaoEnum);

    public List<Banco> findBancoByUnidadeId(long id);

    public List<Banco> findBancoByUnidadeIdAndSituacaoIn(long id, SituacaoEnum situacaoEnum);
}
