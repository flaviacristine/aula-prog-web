package br.edu.unievangelica.domain.unidade;

import br.edu.unievangelica.VirtooApplication;
import br.edu.unievangelica.core.enums.SituacaoEnum;
import br.edu.unievangelica.domain.Unidade;
import br.edu.unievangelica.domain.UnidadeController;
import br.edu.unievangelica.domain.UnidadeService;
import br.edu.unievangelica.domain.disciplina.Disciplina;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

import static org.mockito.Mockito.when;
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
    private List<Unidade> unidadesMock;

    @Mock
    private List<Disciplina> disciplinaListMock;

    @MockBean
    private Unidade unidadeMockito;

    @Mock
    private UnidadeService unidadeServiceMock;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/?language=pt_BR"));
    }

    @Test
    public void findAll() throws Exception {

        when(unidadeServiceMock.findAll()).thenReturn(unidadesMock);

        Unidade unidade = new Unidade();
        unidade.setNome("oi");
        unidade.setCodigo("oi");
        unidade.setSituacao(SituacaoEnum.ATIVO);

        Unidade unidade1 = new Unidade();
        unidade1.setNome("oi");
        unidade1.setCodigo("oi");
        unidade1.setSituacao(SituacaoEnum.ATIVO);

//        unidadesMock.add(unidadeMockito);
        unidadesMock.add(unidade);
        unidadesMock.add(unidade1);

        //1. Convert List of Person objects to JSON
        String arrayToJson = objectMapper.writeValueAsString(unidadesMock);

        System.out.println(arrayToJson);

//        System.out.println(unidadesMock.listIterator());

//        mockMvc.perform(get("/unidade"))
//                .andExpect((ResultMatcher) new ResponseEntity<String>(HttpStatus.OK))
//                .andExpect(MockMvcResultMatchers.content().json(unidadesMock.toString()))
//                .andDo(MockMvcResultHandlers.print());
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
