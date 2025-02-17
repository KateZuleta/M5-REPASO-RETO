package co.bancolombia.aplicacionbancaria.controller;

import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaHistorialPrestamosResponse;
import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaPrestamosResponse;
import co.bancolombia.aplicacionbancaria.service.HistorialPrestamoService;
import co.bancolombia.aplicacionbancaria.service.PrestamoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/historial")
public class HistorialPresamosController {
    private final HistorialPrestamoService historialPrestamoService;
    @GetMapping("/{clienteId}")
    public ResponseEntity<ConsultaHistorialPrestamosResponse> consultarHistorial(@PathVariable Long clienteId) {
        ConsultaHistorialPrestamosResponse historiales = historialPrestamoService.obtenerHistorialDePrestamosDeCliente(clienteId);
        return ResponseEntity.ok(historiales);
    }

    @GetMapping("/TOP3/{clienteId}")
    public ResponseEntity<ConsultaHistorialPrestamosResponse> consultarHistorialTop3(@PathVariable Long clienteId) {
        ConsultaHistorialPrestamosResponse historiales = historialPrestamoService.obtenerUltimos3PrestamosDeCliente(clienteId);
        return ResponseEntity.ok(historiales);
    }
}
