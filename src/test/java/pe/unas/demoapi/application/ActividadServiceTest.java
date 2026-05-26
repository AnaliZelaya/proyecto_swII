package pe.unas.demoapi.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActividadServiceTest {

    private ActividadService service;

    @BeforeEach
    void preparar() {
        service = new ActividadService();
    }

    @Test
    @DisplayName("Debe listar actividades iniciales")
    void debeListarActividadesIniciales() {
        assertEquals(2, service.total());
        assertTrue(service.existe("act01"));
        assertTrue(service.existe("act02"));
    }

    @Test
    @DisplayName("Debe agregar una actividad válida")
    void debeAgregarActividadValida() {
        service.agregar("act03");

        assertEquals(3, service.total());
        assertTrue(service.existe("act03"));
    }

    @Test
    @DisplayName("Debe eliminar una actividad existente")
    void debeEliminarActividadExistente() {
        service.eliminar("act02");

        assertEquals(1, service.total());
        assertFalse(service.existe("act02"));
    }

    @Test
    @DisplayName("No debe aceptar actividad vacía")
    void noDebeAceptarActividadVacia() {

        assertThrows(
            IllegalArgumentException.class,
            () -> service.agregar("")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> service.agregar("   ")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> service.agregar(null)
        );
    }

    @Test
    @DisplayName("No debe aceptar actividad duplicada")
    void noDebeAceptarActividadDuplicada() {

        assertThrows(
            IllegalArgumentException.class,
            () -> service.agregar("act01")
        );
    }
}
