package gm.rh.excepciones;

import java.time.LocalDateTime;

public record ErrorRespuesta(String mensaje, LocalDateTime fecha) {
}
