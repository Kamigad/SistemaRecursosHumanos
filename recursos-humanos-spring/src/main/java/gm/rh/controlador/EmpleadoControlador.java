package gm.rh.controlador;

import gm.rh.modelo.Empleado;
import gm.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}