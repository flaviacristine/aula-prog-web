package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.domain.Unidade;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


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


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findAll() throws Exception {
        get("http://localhost:8181/api/unidade").
                then().
                body("unidades", notNullValue()).
                and().
                body("unidades", not(empty())).
                getClass().equals(Unidade.class);
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

        get("http://localhost:8181/api/unidade/1").
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
        get("http://localhost:8181/api/unidade/find-codigo/1").
                then().
                statusCode(409);
    }

    @Test
    public void verificarCodigoJaCadastradoOk() throws Exception {
        get("http://localhost:8181/api/unidade/find-codigo/12345").
                then().
                statusCode(200);
    }

    @Test
    public void verificarNomeJaCadastradoConflict() throws Exception {
        get("http://localhost:8181/api/unidade/find-nome/unidade").
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
    public void salvar() throws Exception {

        given().
                formParam("nome", "value1").
                formParam("codigo", 1).
                formParam("situacao", "ATIVO").
                formParam("instituicao", "value1").
                when().
                post("http://localhost:8181/api/unidade");
    }

    @Test
    public void salvarEmJason() throws Exception {
        Map<String, Object> unidade = new HashMap<>();
        unidade.put("nome", "John");
        unidade.put("codigo", "Doe");
        unidade.put("situacao", "ATIVO");
//        unidade.put("instituicao", "Doe");

        given().
                contentType(ContentType.JSON).
                body(unidade).
                when().
                post("http://localhost:8181/api/unidade").
                then().
                statusCode(400);
    }

}
