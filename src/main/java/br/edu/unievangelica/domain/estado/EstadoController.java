package br.edu.unievangelica.domain.estado;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado")
public class EstadoController extends ResponseAbstractController {

    @Autowired
    EstadoService estadoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(estadoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(estadoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Estado estado) {
        return jsonResponse(estadoService.save(estado));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Estado estado) {
        return jsonResponse(estadoService.save(estado));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        estadoService.delete(id);
        return jsonResponse(null);
    }

}
