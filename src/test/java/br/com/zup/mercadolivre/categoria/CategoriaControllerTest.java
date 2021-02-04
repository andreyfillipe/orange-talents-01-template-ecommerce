package br.com.zup.mercadolivre.categoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CategoriaControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Salvar uma categoria com sucesso")
    public void salvar() throws Exception {
        Map<String, String> categoriaJson = new HashMap<>();
        categoriaJson.put("nome", "Categoria 1");
        String json = new ObjectMapper().writeValueAsString(categoriaJson);

        URI uri = new URI("/categorias");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                    .post(uri)
                                                    .content(json)
                                                    .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
