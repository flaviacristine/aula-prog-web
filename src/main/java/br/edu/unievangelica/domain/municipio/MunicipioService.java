package br.edu.unievangelica.domain.municipio;

import br.edu.unievangelica.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService extends AbstractService<Municipio> {

    @Autowired
    private MunicipioRepository municipioRepository;

    public List<Municipio> listarPorProvincia(long provincia_id) {
        return municipioRepository.listarPorProvincia(provincia_id);
    }

}
