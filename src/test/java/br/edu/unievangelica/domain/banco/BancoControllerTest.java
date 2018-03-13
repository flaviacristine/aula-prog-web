package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.agenciaConta.AgenciaConta;
import br.edu.unievangelica.domain.endereco.Endereco;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.mantenedora.Mantenedora;
import br.edu.unievangelica.domain.municipio.Municipio;
import br.edu.unievangelica.domain.unidade.Unidade;
import br.edu.unievangelica.domain.unidade.UnidadeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.lang.management.ManagementFactory;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Mockito.spy;


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

    @Spy
    Unidade unidade;

    @Spy
    Endereco endereco;

    @Before
    public void setup() throws Exception {
        // Limpar o banco de dados
        // Cadastrar as dependencias

        Municipio municipio = new Municipio();
        municipio.setId(2);
//
//        System.out.println("Município ==> " + municipio);
//
        endereco = new Endereco();
        endereco.setNumero(RandomStringUtils.randomAlphabetic(3));
        endereco.setBairro(RandomStringUtils.randomAlphabetic(3));
        endereco.setCaixaPostal(RandomStringUtils.randomAlphabetic(3));
        endereco.setLogradouro(RandomStringUtils.randomAlphabetic(3));
//
        unidade = new Unidade();
//        unidade.setId(1);

        unidade.setCodigo(RandomStringUtils.randomAlphabetic(3));
        unidade.setNome(RandomStringUtils.randomAlphabetic(3));
        unidade.setSituacao(SituacaoEnum.ATIVO);
//        unidade.setEndereco(endereco);

        given()
                .contentType("application/json")
                .body(unidade)
                .when()
                .post("http://localhost:8181/api/unidade")
                .then()
                .statusCode(200);
//
//        Instituicao instituicao = new Instituicao();
//        instituicao.setCodigo(RandomStringUtils.randomAlphabetic(3));
//        instituicao.setNome(RandomStringUtils.randomAlphabetic(3));
//        instituicao.setEndereco(endereco);
//        instituicao.setSituacao(SituacaoEnum.ATIVO);
//        instituicao.setNumeroFiscal(RandomStringUtils.randomAlphabetic(3));
////
//        Mantenedora mantenedora = new Mantenedora();
//        mantenedora.setCodigo(RandomStringUtils.randomAlphabetic(3));
//        mantenedora.setNome(RandomStringUtils.randomAlphabetic(3));
//        mantenedora.setEndereco(endereco);
//        mantenedora.setSituacao(SituacaoEnum.ATIVO);
//        mantenedora.setNumeroFiscal(RandomStringUtils.randomAlphabetic(3));
//
//        instituicao.setMantenedora(mantenedora);
//
//        unidade.setInstituicao(instituicao);

//        System.out.println(instituicao.getMantenedora().getNome());
//
//        endereco.setUnidade(unidade);
//        endereco.setMunicipio(municipio);
    }

    @After
    public void finish() throws Exception {
        // Limpar o banco
    }


    @Test
    public void cadastrarItemComDadosValidos() {

        Banco banco = new Banco();
        banco.setNome("banco " + RandomStringUtils.randomAlphabetic(3));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .and()
                .extract().response().jsonPath().getLong("content.id");

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void cadastrarItemComDadosInvalidos() {

        Banco banco = new Banco();
        banco.setNome(RandomStringUtils.randomAlphabetic(90));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(6));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(400);
    }

    @Test
    public void cadastrarItemComDadosDuplicados() {

        Banco banco = new Banco();
        banco.setNome("Banco 1");
        banco.setCodigo("BC 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(500);

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void listarTodosItens() {
        get("http://localhost:8181/api/banco")
                .then()
                .statusCode(200);
    }

    @Test
    public void buscarItemExistentePeloID() {

        Banco banco = new Banco();
        banco.setNome(RandomStringUtils.randomAlphabetic(3));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        get("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void buscarItemInexistentePeloID() {
        get("http://localhost:8181/api/banco/{id}", 0)
                .then()
                .statusCode(500);
    }

    @Test
    public void alterarItemCadastradoComDadosValidos() {

        Banco banco = new Banco();
        banco.setNome(RandomStringUtils.randomAlphabetic(3));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        Banco banco1 = new Banco();
        banco1.setId(lastInsertId);
        banco1.setNome(RandomStringUtils.randomAlphabetic(3));
        banco1.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco1.setSituacao(SituacaoEnum.ATIVO);
        banco1.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco1)
                .when()
                .put("http://localhost:8181/api/banco")
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosInvalidos() {

        Banco banco = new Banco();
        banco.setNome(RandomStringUtils.randomAlphabetic(3));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        Banco banco1 = new Banco();
        banco1.setId(lastInsertId);
        banco1.setNome(RandomStringUtils.randomAlphabetic(90));
        banco1.setCodigo(RandomStringUtils.randomAlphabetic(6));
        banco1.setSituacao(SituacaoEnum.ATIVO);
        banco1.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco1)
                .when()
                .put("http://localhost:8181/api/banco")
                .then()
                .statusCode(400);

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosDuplicados() {

        Banco banco = new Banco();
        banco.setNome("Banco 1");
        banco.setCodigo("BC 1");
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        Banco banco1 = new Banco();
        banco1.setNome("Banco 2");
        banco1.setCodigo("BC 2");
        banco1.setSituacao(SituacaoEnum.ATIVO);
        banco1.setUnidade(unidade);

        long lastInsertIdBanco1 = given()
                .contentType("application/json")
                .body(banco1)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        Banco banco2 = new Banco();
        banco2.setId(lastInsertIdBanco1);
        banco2.setNome("Banco 1");
        banco2.setCodigo("BC 1");
        banco2.setSituacao(SituacaoEnum.ATIVO);
        banco2.setUnidade(unidade);

        given()
                .contentType("application/json")
                .body(banco2)
                .when()
                .put("http://localhost:8181/api/banco")
                .then()
                .statusCode(500);

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/banco/{id}", lastInsertIdBanco1)
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemInexistente() {
        get("http://localhost:8181/api/banco/alterar/{id}", 0)
                .then()
                .statusCode(404);
    }

    @Test
    public void excluirItemComDependencia() {

        Banco banco = new Banco();
        banco.setNome(RandomStringUtils.randomAlphabetic(3));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        banco.setId(lastInsertId);

        AgenciaConta agenciaConta = new AgenciaConta();
        agenciaConta.setBanco(banco);
        agenciaConta.setAgencia(RandomStringUtils.randomAlphabetic(3));
        agenciaConta.setConta(RandomStringUtils.randomAlphabetic(3));
        agenciaConta.setTipoConta(RandomStringUtils.randomAlphabetic(3));
        agenciaConta.setSituacao(SituacaoEnum.ATIVO);

        long lastInsertIdAgenciaConta = given()
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

        delete("http://localhost:8181/api/agencia-conta/{id}", lastInsertIdAgenciaConta)
                .then()
                .statusCode(200);

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);
    }

    @Test
    public void excluirItemCadastrado() {

        Banco banco = new Banco();
        banco.setNome(RandomStringUtils.randomAlphabetic(3));
        banco.setCodigo(RandomStringUtils.randomAlphabetic(3));
        banco.setSituacao(SituacaoEnum.ATIVO);
        banco.setUnidade(unidade);

        lastInsertId = given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getLong("content.id");

        delete("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(200);

        get("http://localhost:8181/api/banco/{id}", lastInsertId)
                .then()
                .statusCode(500);

        excluirItemInexistente();
    }

    @Test
    public void excluirItemInexistente() {
        delete("http://localhost:8181/api/banco/{id}", 0)
                .then()
                .statusCode(404);
    }

    @Test
    public void listarComListaVazia() {
        get("http://localhost:8181/api/banco")
                .then()
                .statusCode(200)
                .body(String.valueOf("content".toString()), hasToString("[]"));
    }

}
