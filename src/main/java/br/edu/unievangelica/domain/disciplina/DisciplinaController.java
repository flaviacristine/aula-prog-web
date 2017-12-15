package br.edu.unievangelica.domain.disciplina;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController extends ResponseAbstractController {

    @Autowired
    DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(disciplinaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        Disciplina disciplina = disciplinaService.findOnde(id);
        return jsonResponse(disciplina);
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Disciplina disciplina) {
        return jsonResponse(disciplinaService.save(disciplina));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Disciplina disciplina) {
        return jsonResponse(disciplinaService.save(disciplina));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        disciplinaService.delete(id);
        return jsonResponse(null);
    }

}
