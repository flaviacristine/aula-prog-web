package br.edu.unievangelica.domain.periodoLetivo;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/periodo-letivo")
public class PeriodoLetivoController extends ResponseAbstractController {

    @Autowired
    PeriodoLetivoService periodoLetivoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(periodoLetivoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(periodoLetivoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody PeriodoLetivo periodoletivo) {
        return jsonResponse(periodoLetivoService.save(periodoletivo));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody PeriodoLetivo periodoletivo) {
        return jsonResponse(periodoLetivoService.save(periodoletivo));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        periodoLetivoService.delete(id);
        return jsonResponse(null);
    }

}
