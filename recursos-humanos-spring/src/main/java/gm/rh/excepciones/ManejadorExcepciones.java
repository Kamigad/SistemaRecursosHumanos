package gm.rh.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(EmpleadoInvalidoExcepcion.class)
    public ResponseEntity<ErrorRespuesta> manejarEmpleadoInvalido(EmpleadoInvalidoExcepcion ex){
        ErrorRespuesta error = new ErrorRespuesta(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(error); //400
    }

    @ExceptionHandler(EmpleadoNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorRespuesta> manejarEmpleadoNoEncontrado(EmpleadoNoEncontradoExcepcion ex){
        ErrorRespuesta error = new ErrorRespuesta(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404
    }
}
