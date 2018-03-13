package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.core.exception.GenericException;
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

    public boolean findUnidadeByNomeIgnoreCase(String nome) {
        List<Unidade> unidades = unidadeRepository.findUnidadeByNomeIgnoreCase(nome);
        if (unidades.isEmpty())
            return false;
        return true;
    }

    public boolean findUnidadeByCodigoIgnoreCase(String codigo) {
        List<Unidade> unidades = unidadeRepository.findUnidadeByCodigoIgnoreCase(codigo);
        if (unidades.isEmpty())
            return false;
        return true;
    }

    @Override
    public Unidade save(Unidade unidade) throws GenericException {
        List<Unidade> unidades = unidadeRepository.findUnidadeByNomeIgnoreCase(unidade.getNome(), unidade.getId());
        List<Unidade> unidades1 = unidadeRepository.findUnidadeByCodigoIgnoreCase(unidade.getCodigo(), unidade.getId());
        if (unidades.isEmpty() && unidades1.isEmpty())
            return super.save(unidade);
        System.out.println("******************************************************** DUPLICADO");
        throw new GenericException("Item duplicado");
    }
}
