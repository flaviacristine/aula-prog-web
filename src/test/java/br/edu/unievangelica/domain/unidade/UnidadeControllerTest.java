package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.Unidade;
import br.edu.unievangelica.domain.instituicao.Instituicao;
import br.edu.unievangelica.domain.instituicao.InstituicaoService;
import io.restassured.http.ContentType;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class UnidadeControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    JsonPath unidade;

    private List<Unidade> unidades;

    public InstituicaoService instituicaoService;


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
    public void listarComListaVazia(){}


    @Test
    public void cadastrarItemComDadosValidos(){
        Unidade unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);

        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

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
    public void cadastrarItemComDadosInvalidos(){}

    @Test
    public void cadastrarItemComDadosDuplicados(){
        Unidade unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);

        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

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
                .statusCode(200)
                .getClass().equals(Unidade.class);
    }


    @Test
    public void buscarItemExistentePeloID() {
        get("http://localhost:8181/api/unidade/{id}", 2)
                .then()
                .statusCode(200)
                .getClass().equals(Unidade.class);
    }

    @Test
    public void buscarItemInexistentePeloID(){
        get("http://localhost:8181/api/unidade/{id}", 1)
                .then()
                .statusCode(500);
    }


    @Test
    public void alterarItemCadastradoComDadosValidos(){}

    @Test
    public void alterarItemCadastradoComDadosInvalidos(){}

    @Test
    public void alterarItemCadastradoComDadosDuplicados(){}

    @Test
    public void alterarItemInexistente(){
        get("http://localhost:8181/api/unidade/alterar/{id}", 1)
                .then()
                .statusCode(404);
    }


    @Test
    public void excluirItemCadastrado(){
        delete("http://localhost:8181/api/unidade/{id}", 1)
                .then()
                .statusCode(200);

        get("http://localhost:8181/api/unidade/{id}", 1)
                .then()
                .statusCode(404);
    }

    @Test
    public void excluirItemComDependencia(){}

    @Test
    public void excluirItemInexistente(){
        delete("http://localhost:8181/api/unidade/{id}", 1)
                .then()
                .statusCode(404);
    }


















    @Test
    public void findAll() throws Exception {
        get("http://localhost:8181/api/unidade").
                then().
                statusCode(200).
                and().
                getClass().
                equals(Unidade.class);
    }

    @Test
    public void verificarCamposContidosEmUnidade() throws Exception {

        unidades = get("http://localhost:8181/api/unidade").getBody().jsonPath().getList("unidades", Unidade.class);

        assertThat(unidades, everyItem(hasProperty("id", notNullValue())));
        assertThat(unidades, everyItem(hasProperty("nome", notNullValue())));
        assertThat(unidades, everyItem(hasProperty("codigo", notNullValue())));
        assertThat(unidades, everyItem(hasProperty("situacao", not(empty()))));
        assertThat(unidades, everyItem(hasProperty("arquivo", isEmptyOrNullString())));
        assertThat(unidades, everyItem(hasProperty("instituicao", not(empty()))));
    }

    @Test
    public void buscarPorId() throws Exception {

        get("http://localhost:8181/api/unidade/{id}", 2).
                then().
                statusCode(200).
                body("id", notNullValue()).
                body("nome", notNullValue()).
                body("codigo", notNullValue()).
                body("situacao", notNullValue()).
                body("arquivo", isEmptyOrNullString()).
                body("instituicao", notNullValue()).
                getClass().equals(Unidade.class);
    }

    @Test
    public void verificarCodigoJaCadastradoConflict() throws Exception {
        get("http://localhost:8181/api/unidade/find-codigo/{id}", 2).
                then().
                statusCode(409);
    }

    @Test
    public void verificarCodigoJaCadastradoOk() throws Exception {
        get("http://localhost:8181/api/unidade/find-codigo/{id}", 12345).
                then().
                statusCode(200);
    }

    @Test
    public void verificarNomeJaCadastradoConflict() throws Exception {
        get("http://localhost:8181/api/unidade/find-nome/unidade 2").
                then().
                statusCode(409);
    }

    @Test
    public void verificarNomeJaCadastradoOk() throws Exception {
        get("http://localhost:8181/api/unidade/find-nome/unidade123").
                then().
                statusCode(200);
    }

    @Test
    public void salvarEmJason() throws Exception {

        Unidade unidade = new Unidade();
        unidade.setNome("Unidade 1");
        unidade.setCodigo("UN1");
        unidade.setSituacao(SituacaoEnum.ATIVO);

        Instituicao instituicao = new Instituicao();
        instituicao.setId(1);

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
    public void apagaAtorExistente() {

        delete("http://localhost:8181/api/unidade/{id}", 1).
            then().
            statusCode(200);

        get("http://localhost:8181/api/unidade/{id}", 1)
            .then()
            .statusCode(404);
    }

}
