package br.edu.unievangelica.domain;

import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.core.exception.GenericException;
import br.edu.unievangelica.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService extends AbstractService<Unidade> {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> findBySituacaoIn() {
        return unidadeRepository.findBySituacaoIn(SituacaoEnum.ATIVO);
    }

    public ResponseEntity<String>  findUnidadeByNomeLike(String nome) {
        List<Unidade> unidades = unidadeRepository.findUnidadeByNomeLike(nome);
        if (!unidades.isEmpty())
            return new ResponseEntity<String>(HttpStatus.OK);
        return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
    }

    @Override
    public Unidade save(Unidade unidade) throws GenericException {
        List<Unidade> unidades = unidadeRepository.findUnidadeByNomeLike(unidade.getNome(), unidade.getId());
        if (!unidades.isEmpty()){
            return null;
        }
        return super.save(unidade);
    }
}
