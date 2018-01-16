package br.edu.unievangelica.domain.frequenciaPadrao;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frequencia-padrao")
public class FrequenciaPadraoController extends ResponseAbstractController {

    @Autowired
    FrequenciaPadraoService frequenciaPadraoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(frequenciaPadraoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(frequenciaPadraoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody FrequenciaPadrao frequenciaPadrao) {
        return jsonResponse(frequenciaPadraoService.save(frequenciaPadrao));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody FrequenciaPadrao frequenciaPadrao) {
        return jsonResponse(frequenciaPadraoService.save(frequenciaPadrao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        frequenciaPadraoService.delete(id);
        return jsonResponse(null);
    }

}

