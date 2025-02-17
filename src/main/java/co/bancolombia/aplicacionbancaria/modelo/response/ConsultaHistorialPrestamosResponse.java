package co.bancolombia.aplicacionbancaria.modelo.response;

import co.bancolombia.aplicacionbancaria.modelo.HistorialPrestamo;
import co.bancolombia.aplicacionbancaria.modelo.Prestamo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsultaHistorialPrestamosResponse {
    private List<Historial> historiales;

    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Historial {
        private Prestamo prestamo;

        private List<HistorialPrestamo> historialPrestamos;
    }
}
