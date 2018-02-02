package br.edu.unievangelica.domain.planoPagamento;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-pagamento")
public class PlanoPagamentoController extends ResponseAbstractController {

    @Autowired
    PlanoPagamentoService planoPagamentoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(planoPagamentoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(planoPagamentoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody PlanoPagamento periodoletivo) {
        return jsonResponse(planoPagamentoService.save(periodoletivo));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody PlanoPagamento periodoletivo) {
        return jsonResponse(planoPagamentoService.save(periodoletivo));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        planoPagamentoService.delete(id);
        return jsonResponse(null);
    }

}
