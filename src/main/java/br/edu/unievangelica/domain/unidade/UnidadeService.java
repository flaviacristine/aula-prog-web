package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService extends AbstractService<Unidade> {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> findBySituacaoIn() {
        return unidadeRepository.findBySituacaoIn(SituacaoEnum.ATIVO);
    }
}
