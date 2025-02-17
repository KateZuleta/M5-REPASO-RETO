package co.bancolombia.aplicacionbancaria.modelo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SimularCuotasResponse {
    private BigDecimal interes;
    private BigDecimal monto;
    private List<BigDecimal> cuotasMensuales;
    private int duracionPrestamo;
}
