package pe.unas.demoapi.presentation;

import org.springframework.web.bind.annotation.*;
import pe.unas.demoapi.application.ProductoService;
import pe.unas.demoapi.domain.Producto;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public void agregar(@RequestParam String nombre) {
        service.agregar(nombre);
    }

    @DeleteMapping
    public void eliminar(@RequestParam String nombre) {
        service.eliminar(nombre);
    }

    @GetMapping("/total")
    public int total() {
        return service.total();
    }
}
