package br.edu.unievangelica.domain.turno;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turno")
public class TurnoController extends ResponseAbstractController {

    @Autowired
    TurnoService turnoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(turnoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(turnoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Turno turno) {
        return jsonResponse(turnoService.save(turno));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Turno turno) {
        return jsonResponse(turnoService.save(turno));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        turnoService.delete(id);
        return jsonResponse(null);
    }

}
