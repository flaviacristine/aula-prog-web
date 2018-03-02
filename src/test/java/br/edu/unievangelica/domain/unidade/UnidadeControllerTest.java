package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.Unidade;
import br.edu.unievangelica.domain.UnidadeService;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.instituicao.InstituicaoService;
import io.restassured.path.json.JsonPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class UnidadeControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private List<Unidade> unidades;


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
        unidade.setNome("Unidade 5");
        unidade.setCodigo("UN5");
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
        get("http://localhost:8181/api/unidade/{id}", 6)
                .then()
                .statusCode(200)
                .getClass();
    }

    @Test
    public void buscarItemInexistentePeloID(){
        get("http://localhost:8181/api/unidade/{id}", 1)
                .then()
                .statusCode(500);
    }

    @Test
    public void alterarItemCadastradoComDadosValidos(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setId(6);
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
        unidade.setId(6);
        unidade.setNome("Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / Unidade 2 / ");
        unidade.setCodigo("UN1");
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
    public void alterarItemCadastradoComDadosDuplicados(){
        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

        Unidade unidade = new Unidade();
        unidade.setId(6);
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
                .statusCode(500);
    }

    @Test
    public void alterarItemInexistente(){
        get("http://localhost:8181/api/unidade/alterar/{id}", 1)
                .then()
                .statusCode(404);
    }

    @Test
    public void excluirItemCadastrado(){
        delete("http://localhost:8181/api/unidade/{id}", 5)
                .then()
                .statusCode(200);

        get("http://localhost:8181/api/unidade/{id}", 5)
                .then()
                .statusCode(500);
    }

    @Test
    public void excluirItemComDependencia(){
        delete("http://localhost:8181/api/unidade/{id}", 7)
                .then()
                .statusCode(500);
    }

    @Test
    public void excluirItemInexistente(){
        delete("http://localhost:8181/api/unidade/{id}", 1)
                .then()
                .statusCode(404);
    }

}
