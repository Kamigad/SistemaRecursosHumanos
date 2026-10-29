package gm.rh.servicio;

import gm.rh.excepciones.EmpleadoInvalidoExcepcion;
import gm.rh.excepciones.EmpleadoNoEncontradoExcepcion;
import gm.rh.modelo.Empleado;
import gm.rh.repositorio.EmpleadoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EmpleadoServicio implements IEmpleadoServicio{

    private final EmpleadoRepositorio empleadoRepositorio;

    public EmpleadoServicio(EmpleadoRepositorio empleadoRepositorio){
        this.empleadoRepositorio = empleadoRepositorio;
    }

    @Override
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleados = empleadoRepositorio.findAll();
        return empleados;
    }

    @Override
    public Empleado buscarEmpleadoId(Integer idEmpleado) {
        Empleado empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        if(empleado != null){
            return empleado;
        }
        else {
            throw new EmpleadoNoEncontradoExcepcion("Empleado no existente: " + idEmpleado);
        }
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        if(Objects.isNull(empleado.getNombreEmpleado()) || empleado.getNombreEmpleado().isBlank()){
            throw new EmpleadoInvalidoExcepcion("No se puede registrar empleados sin nombre");
        } else if(Objects.isNull(empleado.getSueldo())){
            throw  new EmpleadoInvalidoExcepcion("No se puede registrar empleados sin sueldo");
        }
        else if(empleado.getSueldo().compareTo(BigDecimal.ZERO) < 0){
            throw new EmpleadoInvalidoExcepcion("No se puede registrar empleados con sueldos negativos: " + empleado.getSueldo());
        } else {
            return empleadoRepositorio.save(empleado);
        }
    }

    @Override
    public void eliminarEmpleado(Integer idEmpleado) {
        if(empleadoRepositorio.findById(idEmpleado).isEmpty()){
            throw new EmpleadoNoEncontradoExcepcion("Empleado no existente: " + idEmpleado);
        } else {
            empleadoRepositorio.deleteById(idEmpleado);
        }
    }
}
