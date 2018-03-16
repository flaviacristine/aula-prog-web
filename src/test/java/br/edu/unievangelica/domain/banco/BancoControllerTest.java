package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.exception.*;
import br.edu.unievangelica.domain.agenciaConta.AgenciaContaRepository;
import br.edu.unievangelica.domain.unidade.Unidade;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
//@ActiveProfiles(profiles = "test")
public class BancoControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    BancoRepository bancoValidoRepository;
    @MockBean
    BancoService bancoValidoService;
    @MockBean
    AgenciaContaRepository agenciaContaRepository;
    Banco bancoValido, banco2, bancoInvalido;
    private MockMvc mockMvc;
    private List<Banco> bancoList;


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssuredMockMvc.mockMvc(mockMvc);

        //ADICIONA AS DEPENDENIAS
        Unidade unidade = new Unidade();
        unidade.setId(1);

        this.bancoList = new ArrayList<>();

        bancoValido = new Banco();
        bancoValido.setNome("Banco 1");
        bancoValido.setCodigo("BC1");
        bancoValido.setUnidade(unidade);

        this.bancoList.add(bancoValido);

        bancoInvalido = new Banco();
        bancoInvalido.setNome(RandomStringUtils.randomAlphabetic(90));
        bancoInvalido.setCodigo(RandomStringUtils.randomAlphabetic(10));
        bancoInvalido.setUnidade(unidade);

        this.bancoList.add(bancoInvalido);
    }

    @After
    public void finish() throws Exception {
    }

    @Test
    public void listarComListaVazia() throws Exception {
        List<Banco> list = new ArrayList<>();
        when(bancoValidoRepository.findAll()).thenReturn(list);

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco")
                .then()
                .statusCode(200)
                .body(String.valueOf("content".toString()), hasToString("[]"));
    }

    @Test
    public void cadastrarItemComDadosValidos() {
        when(bancoValidoRepository.save(bancoValido)).thenReturn(bancoValido);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(bancoValido)
                .when()
                .post("/banco")
                .then()
                .statusCode(200);
    }

    @Test
    public void cadastrarItemComDadosInvalidos() {
        doThrow(Exception.class).when(bancoValidoRepository).save(bancoValido);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(bancoInvalido)
                .when()
                .post("/banco")
                .then()
                .statusCode(400);
    }

    @Test
    public void cadastrarItemComDadosDuplicados() {
        doThrow(Exception.class).when(bancoValidoService).save(bancoValido);
//        when(bancoValidoRepository.save(bancoValido)).thenThrow(CustomDuplicatedException.class);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(bancoValido)
                .when()
                .post("/banco")
                .then()
                .statusCode(409);
    }

    @Test
    public void alterarItemCadastradoComDadosValidos() {
        when(bancoValidoRepository.save(bancoValido)).thenReturn(bancoValido);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(bancoValido)
                .when()
                .put("/banco")
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosInvalidos() {
        doThrow(Exception.class).when(bancoValidoRepository).save(bancoValido);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(bancoInvalido)
                .when()
                .put("/banco")
                .then()
                .statusCode(400);
    }

    @Test
    public void alterarItemCadastradoComDadosDuplicados() {
//        doThrow(new CustomDuplicatedException(ExceptionMessageCode.MENSAGEM_REGISTRO_DUPLICADO)).when(bancoValidoService).save(bancoValido);
        when(bancoValidoRepository.save(bancoValido)).thenThrow(new GenericException(ExceptionMessageCode.MENSAGEM_REGISTRO_DUPLICADO));

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(bancoValido)
                .when()
                .put("/banco")
                .then()
                .statusCode(409);
    }

    @Test
    public void listarComListaCheia() throws Exception {
        when(bancoValidoRepository.findAll()).thenReturn(this.bancoList);

        List<Banco> response = io.restassured.module.mockmvc.RestAssuredMockMvc
                .when()
                .get("/banco")
                .then()
                .statusCode(200)
                .body("content.codigo", hasItems("BC1"))
                .extract()
                .path("content");
    }

    @Test
    public void buscarItemExistentePeloID() {
//        when(bancoValidoRepository.findOne(bancoValido.getId())).thenReturn(bancoValido);

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco/{id}", bancoValido.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void buscarItemInexistentePeloID() {
        when(bancoValidoService.findOne(bancoValido.getId())).thenThrow(new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND));

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco/{id}", 0)
                .then()
                .statusCode(404);
    }

    @Test
    public void alterarItemInexistente() {
        when(bancoValidoRepository.findOne(bancoValido.getId())).thenThrow(CustomNotFoundException.class);

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco/alterar/{id}", 0)
                .then()
                .statusCode(404);
    }

    @Test
    public void excluirItemComDependencia() {
        when(bancoValidoRepository.findOne(bancoValido.getId())).thenReturn(bancoValido);
        doThrow(CustomDependencyException.class).when(bancoValidoRepository).delete(bancoValido.getId());

        io.restassured.module.mockmvc.RestAssuredMockMvc.delete("/banco/{id}", bancoValido.getId())
                .then()
                .statusCode(500);
    }

    @Test
    public void excluirItemCadastrado() {
//        when(bancoValidoRepository.findOne(bancoValido.getId())).thenReturn(bancoValido);
//        doNothing().when(bancoValidoRepository).delete(bancoValido.getId());

        io.restassured.module.mockmvc.RestAssuredMockMvc.delete("/banco/{id}", bancoValido.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void excluirItemInexistente() {
//        when(bancoValidoRepository.findOne(bancoValido.getId())).thenThrow(new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND));
//        doNothing().when(bancoValidoRepository).delete(bancoValido);

//        io.restassured.module.mockmvc.RestAssuredMockMvc.delete("/banco/{id}", 0)
//                .then()
//                .statusCode(404);

        io.restassured.RestAssured.delete("http://localhost:8181/api/banco/{id}", bancoValido.getId())
                .then()
                .statusCode(404);

    }

}
