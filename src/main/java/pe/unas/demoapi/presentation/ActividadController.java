package pe.unas.demoapi.presentation;

import org.springframework.web.bind.annotation.*;
import pe.unas.demoapi.application.ActividadService;

import java.util.List;

@RestController
@RequestMapping("/actividades")
public class ActividadController {

    private final ActividadService service;

    public ActividadController(ActividadService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> listar() {
        return service.listar();
    }

    @PostMapping
    public String agregar(@RequestParam String nombre) {
        service.agregar(nombre);
        return "Actividad agregado";
    }

    @DeleteMapping
    public String eliminar(@RequestParam String nombre) {
        service.eliminar(nombre);
        return "Actividad eliminado";
    }

    @GetMapping("/total")
    public int total() {
        return service.total();
    }

    @GetMapping("/existe")
    public boolean existe(@RequestParam String nombre) {
        return service.existe(nombre);
    }
}
