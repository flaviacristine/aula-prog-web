package br.edu.unievangelica.domain.agenciaConta;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agencia-conta")
public class AgenciaContaController extends ResponseAbstractController {

    @Autowired
    AgenciaContaService agenciaContaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(agenciaContaService.findAll());
    }

    @GetMapping(value = "/situacao-ativo")
    public ResponseEntity<?> findBySituacaoIn() {
        return jsonResponse(agenciaContaService.findBySituacaoIn());
    }

    @GetMapping(value = "/unidade/{id}")
    public ResponseEntity<?> findAgenciaContaByBancoUnidadeId(@PathVariable long id) {
        return jsonResponse(agenciaContaService.findAgenciaContaByBancoUnidadeId(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(agenciaContaService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody AgenciaConta agenciaConta) {
        return new ResponseEntity<AgenciaConta>(agenciaContaService.save(agenciaConta), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody AgenciaConta agenciaConta) {
        return jsonResponse(agenciaContaService.save(agenciaConta));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        agenciaContaService.delete(id);
        return jsonResponse(null);
    }

}
