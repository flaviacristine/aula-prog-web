package br.edu.unievangelica.domain.mantenedora;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tauan.camargo on 19/12/2017.
 */
@RestController
@RequestMapping("/mantenedora")
public class MantenedoraController extends ResponseAbstractController {

    @Autowired
    MantenedoraService mantenedoraService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(mantenedoraService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(mantenedoraService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Mantenedora mantenedora) {
        return jsonResponse(mantenedoraService.save(mantenedora));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Mantenedora mantenedora) {
        return jsonResponse(mantenedoraService.save(mantenedora));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        mantenedoraService.delete(id);
        return jsonResponse(null);
    }

}
