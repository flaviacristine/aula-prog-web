package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.core.exception.ExceptionMessageCode;
import br.edu.unievangelica.core.exception.GenericException;
import br.edu.unievangelica.domain.agenciaConta.AgenciaConta;
import br.edu.unievangelica.domain.agenciaConta.AgenciaContaRepository;
import br.edu.unievangelica.domain.unidade.Unidade;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
//@ActiveProfiles(profiles = "test")
public class BancoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    BancoRepository bancoRepository;

    @MockBean
    AgenciaContaRepository agenciaContaRepository;

    Banco banco, banco2, banco3;

    private List<Banco> bancoList;


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssuredMockMvc.mockMvc(mockMvc);


        //ADICIONA AS DEPENDENIAS
        Unidade unidade = new Unidade();
        unidade.setId(1);

        this.bancoList = new ArrayList<>();

        banco = new Banco();
        banco.setNome("Banco 1");
        banco.setCodigo("BC1");
        banco.setUnidade(unidade);

        this.bancoList.add(banco);

        banco2 = new Banco();
        banco2.setNome("Banco 2");
        banco2.setCodigo("BC2");
        banco2.setUnidade(unidade);

        this.bancoList.add(banco2);

        banco3 = new Banco();
        banco3.setNome(RandomStringUtils.randomAlphabetic(90));
        banco3.setCodigo(RandomStringUtils.randomAlphabetic(10));
        banco3.setUnidade(unidade);

    }

    @After
    public void finish() throws Exception {
    }

    @Test
    public void listarComListaVazia() throws Exception {

        List<Banco> list = new ArrayList<>();
        when(bancoRepository.findAll()).thenReturn(list);


        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco")
                .then()
                .statusCode(200)
                .body(String.valueOf("content".toString()), hasToString("[]"));
    }

    @Test
    public void cadastrarItemComDadosValidos(){
        when(bancoRepository.save(banco)).thenReturn(banco);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(banco)
                .when()
                .post("/banco")
                .then()
                .statusCode(200);
    }

    @Test
    public void cadastrarItemComDadosInvalidos(){
        doThrow(Exception.class).when(bancoRepository).save(banco);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(banco3)
                .when()
                .post("/banco")
                .then()
                .statusCode(400);
    }

    @Test
    public void cadastrarItemComDadosDuplicados(){
        when(bancoRepository.save(banco)).thenThrow(new GenericException(ExceptionMessageCode.MENSAGEM_REGISTRO_DUPLICADO));

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(banco2)
                .when()
                .post("/banco")
                .then()
                .statusCode(200)
                .body("messages.ERROR", hasItem("Registro já existe !") );
    }

    @Test
    public void alterarItemCadastradoComDadosValidos(){
        when(bancoRepository.save(banco)).thenReturn(banco);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(banco)
                .when()
                .put("/banco")
                .then()
                .statusCode(200);
    }

    @Test
    public void alterarItemCadastradoComDadosInvalidos(){
        doThrow(Exception.class).when(bancoRepository).save(banco);

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(banco3)
                .when()
                .put("/banco")
                .then()
                .statusCode(400);
    }

    @Test
    public void alterarItemCadastradoComDadosDuplicados(){
        when(bancoRepository.save(banco)).thenThrow(new GenericException(ExceptionMessageCode.MENSAGEM_REGISTRO_DUPLICADO));

        io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(banco2)
                .when()
                .put("/banco")
                .then()
                .statusCode(500);
    }

    @Test
    public void listarComListaCheia() throws Exception {
        when(bancoRepository.findAll()).thenReturn(this.bancoList);

        List<Banco> response = io.restassured.module.mockmvc.RestAssuredMockMvc
                .when()
                .get("/banco")
                .then()
                .statusCode(200)
                .body("content.codigo", hasItems("BC1", "BC2"))
                .extract()
                .path("content");
    }

    @Test
    public void buscarItemExistentePeloID() {
        when(bancoRepository.findOne(banco.getId())).thenReturn(banco);

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco/{id}", banco.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void buscarItemInexistentePeloID() {
        when(bancoRepository.findOne(banco.getId())).thenReturn(null);

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco/{id}", 0)
                .then()
                .statusCode(500);
    }

    @Test
    public void alterarItemInexistente(){
        when(bancoRepository.findOne(banco.getId())).thenReturn(null);

        io.restassured.module.mockmvc.RestAssuredMockMvc.get("/banco/alterar/{id}", 0)
                .then()
                .statusCode(404);
    }

    @Test
    public void excluirItemComDependencia() {
        when(bancoRepository.findOne(banco.getId())).thenReturn(banco);
        doThrow(Exception.class).when(bancoRepository).delete(banco.getId());

        io.restassured.module.mockmvc.RestAssuredMockMvc.delete("/banco/{id}", banco.getId())
                .then()
                .statusCode(500);
    }

    @Test
    public void excluirItemCadastrado() {
        when(bancoRepository.findOne(banco.getId())).thenReturn(banco);
        doNothing().when(bancoRepository).delete(banco.getId());

        io.restassured.module.mockmvc.RestAssuredMockMvc.delete("/banco/{id}", banco.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void excluirItemInexistente() {
        when(bancoRepository.findOne(banco.getId())).thenReturn(null);
        doNothing().when(bancoRepository).delete(banco);

        io.restassured.module.mockmvc.RestAssuredMockMvc.delete("/banco/{id}", 0)
                .then()
                .statusCode(404);
    }


}
