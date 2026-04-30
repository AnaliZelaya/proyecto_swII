package pe.unas.demoapi.application;

import org.springframework.stereotype.Service;
import pe.unas.demoapi.domain.Producto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();

    public List<Producto> listar() {
        return productos;
    }

    public void agregar(String nombre) {
        productos.add(new Producto(nombre));
    }

    public void eliminar(String nombre) {
        productos.removeIf(p -> p.getNombre().equals(nombre));
    }

    public int total() {
        return productos.size();
    }
}
