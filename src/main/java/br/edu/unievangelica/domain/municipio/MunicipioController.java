package br.edu.unievangelica.domain.municipio;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/municipio")
public class MunicipioController extends ResponseAbstractController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping(value = "/{provinciaId}")
    public ResponseEntity<?> listarPelaProvincia(@PathVariable("provinciaId") long provinciaId){
        return jsonResponse(municipioService.listarPorProvincia(provinciaId));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Municipio municipio){
        return jsonResponse(municipioService.save(municipio));
    }
}
