package br.edu.unievangelica.domain.instituicaoEscolaridade;

import br.edu.unievangelica.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicao-escolaridade")
public class InstituicaoEscolaridadeController extends ResponseAbstractController {

    @Autowired
    private InstituicaoEscolaridadeService instituicaoEscolaridadeService;

    //Listar Instituição
    @GetMapping
    public ResponseEntity<?> findAll() {
        return jsonResponse(instituicaoEscolaridadeService.findAll());
    }

    //Buscar a instituicao pelo id
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return jsonResponse(instituicaoEscolaridadeService.findOne(id));
    }

    //Salvar instituicao
    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody InstituicaoEscolaridade instituicaoEscolaridade) {
        return jsonResponse(instituicaoEscolaridadeService.save(instituicaoEscolaridade));
    }

    //Alterar instituicao
    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody InstituicaoEscolaridade instituicaoEscolaridade) {
        return jsonResponse(instituicaoEscolaridadeService.save(instituicaoEscolaridade));
    }

    //Deletar instituicao
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        instituicaoEscolaridadeService.delete(id);
        return jsonResponse(null);
    }

}