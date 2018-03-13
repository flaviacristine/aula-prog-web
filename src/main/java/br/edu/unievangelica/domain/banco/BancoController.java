package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banco")
public class BancoController extends ResponseAbstractController {

    @Autowired
    BancoService bancoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return  jsonResponse(bancoService.findAll());
    }

    @GetMapping(value = "/unidade/{id}")
    public ResponseEntity<?> findBancoByUnidadeId(@PathVariable long id) {
        return jsonResponse(bancoService.findBancoByUnidadeId(id));
    }

    @GetMapping(value = "/unidade/{id}/situacao-ativo")
    public ResponseEntity<?> findBancoByUnidadeIdAndSituacaoIn(@PathVariable long id) {
        return jsonResponse(bancoService.findBancoByUnidadeIdAndSituacaoIn(id));
    }

    @GetMapping(value = "/situacao-ativo")
    public ResponseEntity<?> findBySituacaoIn() {
        return jsonResponse(bancoService.findBySituacaoIn());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(bancoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Banco banco) {
        return jsonResponse(bancoService.save(banco));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Banco banco) {
        return jsonResponse(bancoService.save(banco));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        bancoService.delete(id);
        return jsonResponse(null);
    }

}
