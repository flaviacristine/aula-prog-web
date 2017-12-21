package br.edu.unievangelica.domain.curso;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController extends ResponseAbstractController {

    @Autowired
    CursoService cursoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(cursoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(cursoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Curso curso) {
        return jsonResponse(cursoService.save(curso));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Curso curso) {
        return jsonResponse(cursoService.save(curso));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        cursoService.delete(id);
        return jsonResponse(null);
    }

}
