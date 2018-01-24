package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.core.service.AbstractService;
import br.edu.unievangelica.domain.emolumento.Emolumento;
import br.edu.unievangelica.domain.emolumento.EmolumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService extends AbstractService<Banco> {

    @Autowired
    private BancoRepository bancoRepository;

    public List<Banco> findBancoByUnidadeId(long id){
        return bancoRepository.findBancoByUnidadeId(id);
    }
}
