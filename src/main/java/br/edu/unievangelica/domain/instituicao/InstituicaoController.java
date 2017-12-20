package br.edu.unievangelica.domain.instituicao;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController extends ResponseAbstractController {

    @Autowired
    private InstituicaoService instituicaoService;

    /**
     * CRUD Instituicao
     *
     * @return
     */

    //Listar Instituição
    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(instituicaoService.findAll());
    }

    //Buscar a instituicao pelo id
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        Instituicao instituicao = instituicaoService.findOne(id);
        return jsonResponse(instituicao);
    }

    //Salvar instituicao
    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Instituicao instituicao) {
        return jsonResponse(instituicaoService.save(instituicao));
    }

    //Alterar instituicao
    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Instituicao instituicao) {
        return jsonResponse(instituicaoService.save(instituicao));
    }

    //Deletar instituicao
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        instituicaoService.delete(id);
        return jsonResponse(null);
    }

}
