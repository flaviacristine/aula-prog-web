package br.edu.unievangelica.domain.emolumento;

import br.edu.unievangelica.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmolumentoService extends AbstractService<Emolumento> {

    @Autowired
    private EmolumentoRepository emolumentoRepository;

    public List<Emolumento> findEmolumentosByUnidadeId(long id){
        return emolumentoRepository.findEmolumentosByUnidadeId(id);
    }
}
