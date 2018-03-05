package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.banco.Banco;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasToString;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class UnidadeControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setup() throws Exception {
        // Limpar o banco de dados
        // Cadastrar as dependencias
    }

    @After
    public void finish() throws Exception{
        // Limpar o banco
    }

    @Test
    public void listarComListaVazia(){
        get("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .body(String.valueOf("content".toString()), hasToString("[]"));
    }

    @Test
    public void cadastrarItemComDadosValidos(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200);
    }

    @Test
    public void cadastrarItemComDadosInvalidos(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(2);

        Unidade unidade = new Unidade();
        unidade.setNome("Unidade 5");
        unidade.setCodigo("UN555555555");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(400);
    }

    @Test
    public void cadastrarItemComDadosDuplicados(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(500);
    }

    @Test
    public void listarTodosItens(){
        get("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200);
    }

    @Test
    public void buscarItemExistentePeloID() {
        get("http://localhost:8181/api/unidade/{id}", 2)
                .then()
                .statusCode(200);
    }

    @Test
    public void buscarItemInexistentePeloID(){
        get("http://localhost:8181/api/unidade/{id}", 0)
                .then()
                .statusCode(500);
    }

    @Test
    public void alterarItemCadastradoComDadosValidos(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setId(2);
        unidade.setNome("Unidade 2");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .put("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosInvalidos(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setId(2);
        unidade.setNome("Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / ");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .put("http://localhost:8181/api/unidade")
                .then()
                .statusCode(400);
    }

    @Test
    public void alterarItemCadastradoComDadosDuplicados(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setId(2);
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .put("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemInexistente(){
        get("http://localhost:8181/api/unidade/alterar/{id}", 0)
                .then()
                .statusCode(404);
    }

    @Test
    public void excluirItemComDependencia(){
        Unidade unidade = new Unidade();
        unidade.setId(2);

        Banco banco = new Banco();
        banco.setUnidade(unidade);
        banco.setCodigo("1");
        banco.setNome("Banco");
        banco.setSituacao(SituacaoEnum.ATIVO);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/unidade/{id}", 2)
                .then()
                .statusCode(500);

        delete("http://localhost:8181/api/banco/{id}", 2)
                .then()
                .statusCode(200);
    }

    @Test
    public void excluirItemCadastrado(){
        delete("http://localhost:8181/api/unidade/{id}", 2)
                .then()
                .statusCode(200);

        get("http://localhost:8181/api/unidade/{id}", 2)
                .then()
                .statusCode(500);
    }

    @Test
    public void excluirItemInexistente(){
        delete("http://localhost:8181/api/unidade/{id}", 0)
                .then()
                .statusCode(404);
    }

}
