package gm.rh.controlador;

import gm.rh.modelo.Empleado;
import gm.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados") // http:localhost:8080/api/empleados
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);
    private final IEmpleadoServicio iEmpleadoServicio;

    public EmpleadoControlador(IEmpleadoServicio iEmpleadoServicio){
        this.iEmpleadoServicio = iEmpleadoServicio;
    }

    @GetMapping("/")
    public List<Empleado> obtenerEmpleados(){
        var empleados = iEmpleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> logger.debug(empleado.toString()));
        return empleados;
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> obtenerEmpleadoId(@PathVariable Integer idEmpleado){
        var empleado = this.iEmpleadoServicio.buscarEmpleadoId(idEmpleado);
        logger.debug(empleado.toString());
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.debug(empleado.toString());
        return this.iEmpleadoServicio.guardarEmpleado(empleado);
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer idEmpleado, @RequestBody Empleado empleadoActualizado){
        var empleado = this.iEmpleadoServicio.buscarEmpleadoId(idEmpleado);
        empleado.setNombreEmpleado(empleadoActualizado.getNombreEmpleado());
        empleado.setDepartamentoEmpleado(empleadoActualizado.getDepartamentoEmpleado());
        empleado.setSueldo(empleadoActualizado.getSueldo());
        this.iEmpleadoServicio.guardarEmpleado(empleado);
        logger.debug("Empleado Nuevo: " + empleado.toString());
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Integer idEmpleado){
        this.iEmpleadoServicio.eliminarEmpleado(idEmpleado);
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}