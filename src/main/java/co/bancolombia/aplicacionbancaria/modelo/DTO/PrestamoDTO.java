package co.bancolombia.aplicacionbancaria.modelo.DTO;

import co.bancolombia.aplicacionbancaria.modelo.enums.EstadoPrestamo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PrestamoDTO {
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal monto;

    @NotNull(message = "El plazo es obligatorio")
    @Positive(message = "El plazo debe ser mayor a cero")
    @Range(min = 1, max = 90, message = "El plazo debe estar entre 1 y 90")
    @JsonProperty("duracion_meses")
    private int duracionMeses;

    @NotNull(message = "El estado del prestamo es obligatorio")
    private EstadoPrestamo estado;

    @NotNull(message = "El id del cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser mayor a cero")
    @JsonProperty("client_id")
    private long clientId;
}
