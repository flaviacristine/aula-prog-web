package br.edu.unievangelica.domain.agenciaConta;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.core.service.AbstractService;
import br.edu.unievangelica.domain.banco.Banco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciaContaService extends AbstractService<AgenciaConta> {

    @Autowired
    private AgenciaContaRepository agenciaContaRepository;

    public List<AgenciaConta> findBySituacaoIn() {
        return agenciaContaRepository.findBySituacaoIn(SituacaoEnum.ATIVO);
    }

    public List<AgenciaConta> findAgenciaContaByBancoUnidadeId(long id){
        return agenciaContaRepository.findAgenciaContaByBancoUnidadeId(id);
    }
}
