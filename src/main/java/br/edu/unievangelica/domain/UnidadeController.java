package br.edu.unievangelica.domain;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/unidade")
public class UnidadeController extends ResponseAbstractController {

    @Autowired
    UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        Map<String, Object> result = new HashMap<>();
        result.put("unidades", unidadeService.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/situacao-ativo")
    public ResponseEntity<?> findBySituacaoIn() {
        return jsonResponse(unidadeService.findBySituacaoIn());
    }

    @GetMapping(value = "/find-nome/{nome}")
    public ResponseEntity<?> findUnidadeByNomeIgnoreCase(@PathVariable String nome) {
        HttpStatus status = unidadeService.findUnidadeByNomeIgnoreCase(nome) == true ? HttpStatus.CONFLICT : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/find-codigo/{codigo}")
    public ResponseEntity<?> findUnidadeByCodigoIgnoreCase(@PathVariable String codigo) {
        HttpStatus status = unidadeService.findUnidadeByCodigoIgnoreCase(codigo) == true ? HttpStatus.CONFLICT : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable long id) {
        Unidade unidade = unidadeService.findOne(id);
        HttpStatus status = (unidade == null)? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<Unidade>(unidade, status);
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
