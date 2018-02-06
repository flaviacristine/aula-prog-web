package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.core.controller.JsonResponse;
import br.edu.unievangelica.core.enums.SituacaoEnum;
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
        Unidade unidade = unidadeRepository.findUnidadeByNomeLike(nome);
        if (unidade != null)
            return new ResponseEntity<String>(HttpStatus.OK);
        return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
    }
}
