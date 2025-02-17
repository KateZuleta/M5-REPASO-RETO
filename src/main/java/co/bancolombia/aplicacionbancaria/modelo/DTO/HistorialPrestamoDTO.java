package co.bancolombia.aplicacionbancaria.modelo.DTO;

import co.bancolombia.aplicacionbancaria.modelo.Prestamo;
import co.bancolombia.aplicacionbancaria.modelo.enums.EstadoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialPrestamoDTO {
    private Prestamo prestamo;
    private BigDecimal monto;
    private EstadoPrestamo estado;
}
