GUÍA PRÁCTICA – SESIÓN 6 (VERSIÓN WINDOWS)
Integración inicial del sistema – Spring Boot
OBJETIVO
✔ Integrar Controller → Service → Domain
✔ Ejecutar Spring Boot en Windows
✔ Probar API (GET, POST, DELETE)

1. REQUISITOS EN WINDOWS
✔ Java 17 instalado
✔ Maven instalado
✔ VS Code o IntelliJ
✔ CMD / PowerShell
2. UBICAR PROYECTO
Abre CMD o PowerShell:
 
cd C:\Users\TU_USUARIO\Documents\const_sw2\demoapi
dir
 
Debe aparecer:
 
pom.xml
mvnw.cmd
src
 
IMPORTANTE:
En Windows se usa:
 
mvnw.cmd
 
NO ./mvnw
3. CREAR ESTRUCTURA
 
mkdir src\main\java\pe\unas\demoapi\domain
mkdir src\main\java\pe\unas\demoapi\application
mkdir src\main\java\pe\unas\demoapi\presentation
 

4. CREAR ARCHIVOS (EN WINDOWS)
Puedes hacerlo de 2 formas:
🔹 OPCIÓN A: VS CODE (RECOMENDADO)
👉 Abrir carpeta demoapi
👉 Click derecho → New File
Crear:
 
domain/Producto.java
application/ProductoService.java
presentation/ProductoController.java
 

🔹 OPCIÓN B: CMD
 
notepad src\main\java\pe\unas\demoapi\domain\Producto.java
 

5. CÓDIGO 
✔ Producto.java
 
package pe.unas.demoapi.domain;
public class Producto {
    private String nombre;
    public Producto(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
}
 
✔ ProductoService.java
 
package pe.unas.demoapi.application;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ProductoService {
    private List<String> productos = new ArrayList<>();
    public ProductoService() {
        productos.add("Laptop");
        productos.add("Mouse");
    }
    public List<String> listar() {
        return productos;
    }
    public void agregar(String nombre) {
        productos.add(nombre);
    }
    public void eliminar(String nombre) {
        productos.remove(nombre);
    }
}
 
✔ ProductoController.java
 
package pe.unas.demoapi.presentation;
import org.springframework.web.bind.annotation.*;
import pe.unas.demoapi.application.ProductoService;
import java.util.List;
@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service) {
        this.service = service;
    }
    @GetMapping
    public List<String> listar() {
        return service.listar();
    }
    @PostMapping
    public String agregar(@RequestParam String nombre) {
        service.agregar(nombre);
        return "Producto agregado";
    }
    @DeleteMapping
    public String eliminar(@RequestParam String nombre) {
        service.eliminar(nombre);
        return "Producto eliminado";
    }
}
 
6. EJECUTAR EN WINDOWS

🔹 Opción 1 (Maven instalado)
 
mvn spring-boot:run
 
🔹 Opción 2 (Wrapper)
 
mvnw.cmd spring-boot:run
 
SEÑAL CORRECTA
 
Tomcat started on port 8080
 
7. PROBAR
🔹 GET
👉 Navegador:
 
http://localhost:8080/productos
 
🔹 POST
En CMD:
 
curl -X POST "http://localhost:8080/productos?nombre=Teclado"
 
🔹 DELETE
 
curl -X DELETE "http://localhost:8080/productos?nombre=Mouse"
 
8. EJERCICIO
Agregar:
 
GET /productos/total
 

Código extra
Service:
 
public int total() {
    return productos.size();
}
 
Controller:
 
@GetMapping("/total")
public int total() {
    return service.total();
}
 
9. GIT (EN WINDOWS)
 
git status
git add .
git commit -m "Integración sesión 6"
git push
 
ERRORES COMUNES EN WINDOWS
Error	Solución
mvnw no funciona	usar mvnw.cmd
curl no funciona	usar Postman
404	revisar package
puerto ocupado	cerrar app anterior

