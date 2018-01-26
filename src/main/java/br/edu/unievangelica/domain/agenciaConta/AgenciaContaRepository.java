package br.edu.unievangelica.domain.agenciaConta;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenciaContaRepository extends JpaRepository<AgenciaConta, Long> {

    public List<AgenciaConta> findBySituacaoIn(SituacaoEnum situacaoEnum);

    public List<AgenciaConta> findAgenciaContaByBancoUnidadeId(long id);
}
