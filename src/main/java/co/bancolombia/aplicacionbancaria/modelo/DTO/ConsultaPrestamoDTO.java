package co.bancolombia.aplicacionbancaria.modelo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsultaPrestamoDTO {
    @NotNull(message = "El id del prestamo es obligatorio!")
    @Positive(message = "El id del prestamo debe ser mayor a cero!")
    @JsonProperty("prestamo_id")
    private Long prestamoId;

    @NotNull(message = "El id del cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser mayor a cero")
    @JsonProperty("client_id")
    private Long clientId;
}
