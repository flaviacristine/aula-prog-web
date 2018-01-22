package br.edu.unievangelica.domain.emolumento;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emolumento")
public class EmolumentoController extends ResponseAbstractController {

    @Autowired
    EmolumentoService emolumentoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(emolumentoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(emolumentoService.findOne(id));
    }

    @GetMapping(value = "/unidade/{id}")
    public ResponseEntity<?> findByUnidadeId(@PathVariable long id) {
        return jsonResponse(emolumentoService.findEmolumentosByUnidadeId(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Emolumento emolumento) {
        return jsonResponse(emolumentoService.save(emolumento));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Emolumento emolumento) {
        return jsonResponse(emolumentoService.save(emolumento));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        emolumentoService.delete(id);
        return jsonResponse(null);
    }

}
