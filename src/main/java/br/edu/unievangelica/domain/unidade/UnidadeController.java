package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidade")
public class UnidadeController extends ResponseAbstractController {

    @Autowired
    UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(unidadeService.findAll());
    }

    @GetMapping(value = "/situacao-ativo")
    public ResponseEntity<?> findBySituacaoIn() {
        return jsonResponse(unidadeService.findBySituacaoIn());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(unidadeService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Unidade unidade) {
        return jsonResponse(unidadeService.save(unidade));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Unidade unidade) {
        return jsonResponse(unidadeService.save(unidade));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        unidadeService.delete(id);
        return jsonResponse(null);
    }

}
