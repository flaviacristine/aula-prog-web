package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.agenciaConta.AgenciaConta;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.banco.Banco;
import br.edu.unievangelica.domain.unidade.Unidade;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BancoControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    // Pegar o id do último item cadastrado no banco para os testes.
    long lastInsertId;

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
        get("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .body(String.valueOf("content".toString()), hasToString("[]"));

        cadastrarItemComDadosValidos();
    }

    @Test
    public void cadastrarItemComDadosValidos(){
        Unidade unidade = new Unidade();
        unidade.setId(1);

        Banco banco = new Banco();
        banco.setNome("Banco 1");
        banco.setCodigo("BC 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId =  given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .and()
                .extract().response().as(Banco.class).getId();

        cadastrarItemComDadosInvalidos();
    }

    @Test
    public void cadastrarItemComDadosInvalidos(){
        Unidade unidade = new Unidade();
        unidade.setId(1);

        Banco banco = new Banco();
        banco.setNome("Banco 111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        banco.setCodigo("BC 1111111111");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(400);

        cadastrarItemComDadosDuplicados();
    }

    @Test
    public void cadastrarItemComDadosDuplicados(){
        Unidade unidade = new Unidade();
        unidade.setId(1);

        Banco banco = new Banco();
        banco.setNome("Banco 1");
        banco.setCodigo("BC 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(500);

        listarTodosItens();
    }

    @Test
    public void listarTodosItens(){
        get("http://localhost:8181/api/banco")
                .then()
                .statusCode(200);

        buscarItemExistentePeloID();
    }

    @Test
    public void buscarItemExistentePeloID() {

        get("http://localhost:8181/api/banco/{id}", lastInsertId)
        .then()
        .statusCode(200);

        buscarItemInexistentePeloID();
    }

    @Test
    public void buscarItemInexistentePeloID(){
        get("http://localhost:8181/api/banco/{id}", 0)
                .then()
                .statusCode(500);

        alterarItemCadastradoComDadosValidos();
    }

    @Test
    public void alterarItemCadastradoComDadosValidos(){
        Unidade unidade = new Unidade();
        unidade.setId(1);

        Banco banco = new Banco();
        banco.setId(lastInsertId);
        banco.setNome("Banco 2");
        banco.setCodigo("BC 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .put("http://localhost:8181/api/banco")
                .then()
                .statusCode(200);

        alterarItemCadastradoComDadosInvalidos();
    }

    @Test
    public void alterarItemCadastradoComDadosInvalidos(){
        Unidade unidade = new Unidade();
        unidade.setId(1);

        Banco banco = new Banco();
        banco.setId(lastInsertId);
        banco.setNome("Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / Banco 2 / ");
        banco.setCodigo("UN 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .put("http://localhost:8181/api/banco")
                .then()
                .statusCode(400);

        alterarItemCadastradoComDadosDuplicados();
    }

    @Test
    public void alterarItemCadastradoComDadosDuplicados(){
        Unidade unidade = new Unidade();
        unidade.setId(1);

        Banco banco = new Banco();
        banco.setId(lastInsertId);
        banco.setNome("Banco 1");
        banco.setCodigo("BC 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .put("http://localhost:8181/api/banco")
                .then()
                .statusCode(200);

        alterarItemInexistente();
    }

    @Test
    public void alterarItemInexistente(){
        get("http://localhost:8181/api/banco/alterar/{id}", 0)
                .then()
                .statusCode(404);

        excluirItemComDependencia();
    }

    @Test
    public void excluirItemComDependencia(){
        Banco banco = new Banco();
        banco.setId(lastInsertId);

        AgenciaConta agenciaConta = new AgenciaConta();
        agenciaConta.setBanco(banco);
        agenciaConta.setAgencia("agencia");
        agenciaConta.setConta("conta");
        agenciaConta.setTipoConta("tipoConta");
        agenciaConta.setSituacao(SituacaoEnum.ATIVO);

        long lastInsertAgenciaId = given()
                .contentType("application/json")
                .body(agenciaConta)
                .when()
                .post("http://localhost:8181/api/agencia-conta")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response()
                .as(AgenciaConta.class).getId();

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(500);

        delete("http://localhost:8181/api/agencia-conta/{id}", lastInsertAgenciaId)
                .then()
                .statusCode(200);

        excluirItemCadastrado();
    }

    @Test
    public void excluirItemCadastrado(){
        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);

        get("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(500);

        excluirItemInexistente();
    }

    @Test
    public void excluirItemInexistente(){
        delete("http://localhost:8181/api/banco/{id}", 0)
                .then()
                .statusCode(404);
    }

}
