package co.bancolombia.aplicacionbancaria.controller;

import co.bancolombia.aplicacionbancaria.modelo.DTO.ConsultaPrestamoDTO;
import co.bancolombia.aplicacionbancaria.modelo.DTO.GestionarPrestamoDTO;
import co.bancolombia.aplicacionbancaria.modelo.DTO.PrestamoDTO;
import co.bancolombia.aplicacionbancaria.modelo.DTO.SimularCuotasDTO;
import co.bancolombia.aplicacionbancaria.modelo.enums.EstadoPrestamo;
import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaPrestamoResponse;
import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaPrestamosResponse;
import co.bancolombia.aplicacionbancaria.modelo.response.SimularCuotasResponse;
import co.bancolombia.aplicacionbancaria.service.PrestamoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        String response = prestamoService.solicitudPrestamo(prestamoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/gestionar")
    public ResponseEntity<String> gestionarPrestamo(@RequestBody GestionarPrestamoDTO gestionarPrestamoDTO) {
        String response = prestamoService.gestionarEstadoPrestamo(gestionarPrestamoDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/historial/{clienteId}")
    public ResponseEntity<ConsultaPrestamosResponse> consultarHistorial(@PathVariable Long clienteId) {
        ConsultaPrestamosResponse prestamos = prestamoService.consultaPrestamos(clienteId);
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/consulta")
    public ResponseEntity<ConsultaPrestamoResponse> consultarPrestamo(@RequestBody ConsultaPrestamoDTO consultaPrestamoDTO) {
        ConsultaPrestamoResponse prestamo = prestamoService.consultaPrestamo(consultaPrestamoDTO);
        return ResponseEntity.ok(prestamo);
    }

    @PostMapping("/simular")
    public ResponseEntity<SimularCuotasResponse> solicitarPrestamo(@RequestBody SimularCuotasDTO prestamoDTO) {
        SimularCuotasResponse response = prestamoService.calcularCuota(prestamoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
