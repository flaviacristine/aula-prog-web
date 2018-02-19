package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.domain.Unidade;
import br.edu.unievangelica.domain.UnidadeController;
import br.edu.unievangelica.domain.UnidadeService;
import br.edu.unievangelica.domain.disciplina.Disciplina;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VirtooApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class UnidadeControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @InjectMocks
    private UnidadeController unidadeController;

    @Mock
    private Unidade unidadeMock;

    @Mock
    private List<Disciplina> disciplinaListMock;

    @Mock
    private UnidadeService unidadeServiceMock;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/?language=pt_BR"));
    }

//    @Test
//    public void listarDisciplinas() throws Exception {
//        List<Unidade> unidadeListMock = new ArrayList();
//        unidadeListMock.add(unidadeMock);
//
//
//        Unidade unidade = new Unidade();
//        unidade.setNome("oi");
//        unidade.setCodigo("oi");
//        unidade.setSituacao(SituacaoEnum.ATIVO);
//        unidade.setDisciplinas(disciplinaListMock);
//
//        when(unidadeServiceMock.findAll()).thenReturn(unidadeListMock);
//
//        mockMvc.perform(get("/unidade")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$[0].nome", CoreMatchers.is("oi")))
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void returnsThePostAsJson() throws Exception {
        List<Unidade> unidadeListMock = new ArrayList();
        unidadeListMock.add(unidadeMock);
        System.out.println(unidadeListMock.iterator().next());
        mockMvc.perform(get("/unidade/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(jsonPath());
    }
}
