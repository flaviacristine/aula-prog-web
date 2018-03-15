package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.core.exception.GenericException;
import br.edu.unievangelica.core.service.AbstractService;
import br.edu.unievangelica.core.service.IService;
import br.edu.unievangelica.domain.unidade.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService extends AbstractService<Banco> implements IService<Banco> {

    @Autowired
    private BancoRepository bancoRepository;

    public List<Banco> findBySituacaoIn() {
        return bancoRepository.findBySituacaoIn(SituacaoEnum.ATIVO);
    }

    public List<Banco> findBancoByUnidadeId(long id){
        return bancoRepository.findBancoByUnidadeId(id);
    }

    public List<Banco> findBancoByUnidadeIdAndSituacaoIn(long id){
        return bancoRepository.findBancoByUnidadeIdAndSituacaoIn(id, SituacaoEnum.ATIVO);
    }


    @Override
    public List<Banco> findAll() {
        System.out.println(" ------------ BANCO SERVICE ------------------ ");
        return (List<Banco>) repository.findAll();
    }

}
