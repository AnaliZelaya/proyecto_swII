package pe.unas.demoapi.presentation;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(
    classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
)
class ActividadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarActividades_debeRetornarStatus200YListaInicial()
            throws Exception {

        mockMvc.perform(get("/actividades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("act01"))
                .andExpect(jsonPath("$[1]").value("act02"));
    }

    @Test
    void agregarActividad_debeRetornarMensajeYActualizarLista()
            throws Exception {

        mockMvc.perform(
                post("/actividades")
                        .param("nombre", "act03")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Actividad agregado"));

        mockMvc.perform(get("/actividades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasItem("act03")));
    }

    @Test
    void eliminarActividad_debeRetirarActividadDeLaLista()
            throws Exception {

        mockMvc.perform(
                delete("/actividades")
                        .param("nombre", "act02")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Actividad eliminado"));

        mockMvc.perform(get("/actividades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(hasItem("act02"))));
    }

    @Test
    void totalActividades_debeRetornarCantidadInicial()
            throws Exception {

        mockMvc.perform(get("/actividades/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void existeActividad_debeRetornarTrueCuandoExiste()
            throws Exception {

        mockMvc.perform(
                get("/actividades/existe")
                        .param("nombre", "act01")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
