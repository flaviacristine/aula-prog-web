package br.edu.unievangelica.domain.banco;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.domain.unidade.Unidade;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
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

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasToString;
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

    @InjectMocks
    private BancoController bancoController;

    @MockBean
    BancoRepository  bancoRepository;


    private List<Banco> bancoList;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssuredMockMvc.mockMvc( mockMvc );



        //ADICIONA AS DEPENDENIAS
        Unidade unidade = new Unidade();
        unidade.setId(1);

        this.bancoList = new ArrayList<>();

        Banco bc1 = new Banco();;
        bc1.setNome("Banco 1");
        bc1.setCodigo("BC1");
        bc1.setUnidade(unidade);

        this.bancoList.add(bc1);

        Banco bc2 = new Banco();
        bc2.setNome("Banco 2");
        bc2.setCodigo("BC2");
        bc2.setUnidade(unidade);

        this.bancoList.add(bc2);

    }

    @Before

    @After
    public void finish() throws Exception{
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
    public void listarComListaCheia() throws Exception {

        when(bancoRepository.findAll()).thenReturn(this.bancoList);


        List<Banco> response = io.restassured.module.mockmvc.RestAssuredMockMvc
            .when()
                .get("/banco")
            .then()
                .statusCode(200)
                .body("content.codigo", hasItems("BC1", "BC2", "BC3"))
            .extract()
                .path("content");

    }


}
