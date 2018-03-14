package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.banco.Banco;
import br.edu.unievangelica.domain.endereco.Endereco;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.municipio.Municipio;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Spy;
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

    // Pegar o id do último item cadastrado no banco para os testes.
    long lastInsertId;

    Unidade unidade = new Unidade();

    Endereco endereco = new Endereco();

    Instituicao instituicao = new Instituicao();

    @Before
    public void setup() throws Exception {
        // Limpar o banco de dados
        // Cadastrar as dependencias

        instituicao.setId(1);

        endereco.setId(5);
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

        unidade.setNome("Unidade " + RandomStringUtils.randomAlphabetic(3));
        unidade.setCodigo(RandomStringUtils.randomAlphabetic(3));
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .and()
                .extract().response().jsonPath().getLong("content.id");

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void cadastrarItemComDadosInvalidos(){

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
        get("http://localhost:8181/api/unidade").then().statusCode(200);
    }

    @Test
    public void buscarItemExistentePeloID() {
        unidade = new Unidade();
        unidade.setNome("Instituto Superior Politécnico Vida 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        get("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
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

        unidade = new Unidade();
        unidade.setNome("Instituto Superior Politécnico Vida 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        unidade = new Unidade();
        unidade.setId(lastInsertId);
        unidade.setNome("Instituto Superior Politécnico Vida 2");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .put("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosInvalidos(){

        unidade = new Unidade();
        unidade.setNome("Instituto Superior Politécnico Vida 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        Unidade unidade = new Unidade();
        unidade.setId(lastInsertId);
        unidade.setNome(RandomStringUtils.randomAlphabetic(90));
        unidade.setCodigo(RandomStringUtils.randomAlphabetic(10));
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .put("http://localhost:8181/api/unidade")
                .then()
                .statusCode(400);

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosDuplicados(){

        unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .put("http://localhost:8181/api/unidade")
                .then()
                .statusCode(500);

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
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
        unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        Banco banco = new Banco();
        banco.setUnidade(unidade);
        banco.setCodigo("1");
        banco.setNome("Banco");
        banco.setSituacao(SituacaoEnum.ATIVO);

        long lastInsertBancoId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .and()
                .extract().response().as(Banco.class).getId();

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(500);

        delete("http://localhost:8181/api/banco/{id}", lastInsertBancoId)
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void excluirItemCadastrado(){
        unidade = new Unidade();
        unidade.setNome(RandomStringUtils.randomAlphabetic(10));
        unidade.setCodigo(RandomStringUtils.randomAlphabetic(3));
        unidade.setSituacao(SituacaoEnum.ATIVO);
        unidade.setInstituicao(instituicao);
        unidade.setEndereco(endereco);

        lastInsertId = given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        delete("http://localhost:8181/api/unidade/{id}", lastInsertId)
                .then()
                .statusCode(200);

        get("http://localhost:8181/api/unidade/{id}", lastInsertId)
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
