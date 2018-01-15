package br.edu.unievangelica.domain.avaliacaoPadrao;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao-padrao")
public class AvaliacaoPadraoController extends ResponseAbstractController {

    @Autowired
    AvaliacaoPadraoService avaliacaoPadraoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(avaliacaoPadraoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(avaliacaoPadraoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody AvaliacaoPadrao avaliacaoPadrao) {
        return jsonResponse(avaliacaoPadraoService.save(avaliacaoPadrao));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody AvaliacaoPadrao avaliacaoPadrao) {
        return jsonResponse(avaliacaoPadraoService.save(avaliacaoPadrao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        avaliacaoPadraoService.delete(id);
        return jsonResponse(null);
    }

}

