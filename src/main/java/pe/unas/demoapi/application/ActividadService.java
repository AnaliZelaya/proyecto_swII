package pe.unas.demoapi.application;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadService {

    private final List<String> actividades = new ArrayList<>();

    public ActividadService() {
        actividades.add("act01");
        actividades.add("act02");
    }

    public List<String> listar() {
        return actividades;
    }

    public void agregar(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                "El nombre del actividad es obligatorio"
            );
        }

        String nombreLimpio = nombre.trim();

        if (actividades.contains(nombreLimpio)) {
            throw new IllegalArgumentException(
                "La actividad ya existe"
            );
        }

        actividades.add(nombreLimpio);
    }

    public void eliminar(String nombre) {
        actividades.remove(nombre);
    }

    public int total() {
        return actividades.size();
    }

    public boolean existe(String nombre) {
        return actividades.contains(nombre);
    }

        public String clasificarActividad(int valor) {

        validarRango(valor);

        if (valor >= 80) {
            return "ALTA";
        }

        if (valor >= 50) {
            return "MEDIA";
        }

        return "BAJA";
    }

    public boolean esAceptable(int valor) {

        validarRango(valor);

        return valor >= 70;
    }

    private void validarRango(int valor) {
        if (valor < 0 || valor > 100) {
            throw new IllegalArgumentException("Valor fuera de rango");
        }
    }
}
