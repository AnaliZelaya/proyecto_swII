package pe.unas.demoapi;

import org.junit.jupiter.api.Test;
import pe.unas.demoapi.application.ActividadService;

import static org.junit.jupiter.api.Assertions.*;

class ActividadServiceTest {

    private final ActividadService service = new ActividadService();

    @Test
    void clasificaActividadAlta() {
        assertEquals("ALTA", service.clasificarActividad(85));
    }

    @Test
    void clasificaActividadMedia() {
        assertEquals("MEDIA", service.clasificarActividad(60));
    }

    @Test
    void clasificaActividadBaja() {
        assertEquals("BAJA", service.clasificarActividad(30));
    }

    @Test
    void rechazaActividadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> service.clasificarActividad(-1));
    }

    @Test
    void rechazaActividadMayorACien() {
        assertThrows(IllegalArgumentException.class, () -> service.clasificarActividad(101));
    }

    @Test
    void esAceptable_debeRetornarTrueCuandoEsMayorOIgualAlUmbral() {
        assertTrue(service.esAceptable(70));
        assertTrue(service.esAceptable(90));
    }

    @Test
    void esAceptable_debeRetornarFalseCuandoEsMenorAlUmbral() {
        assertFalse(service.esAceptable(40));
    }

    @Test
    void esAceptable_debeRechazarValoresFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> service.esAceptable(-5));
        assertThrows(IllegalArgumentException.class, () -> service.esAceptable(105));
    }
}
