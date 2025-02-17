package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.modelo.Cliente;
import co.bancolombia.aplicacionbancaria.modelo.DTO.HistorialPrestamoDTO;
import co.bancolombia.aplicacionbancaria.modelo.HistorialPrestamo;
import co.bancolombia.aplicacionbancaria.modelo.Prestamo;
import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaHistorialPrestamosResponse;
import co.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import co.bancolombia.aplicacionbancaria.repository.HistorialPrestamoRepository;
import co.bancolombia.aplicacionbancaria.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class HistorialPrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HistorialPrestamoRepository historialPrestamoRepository;
    private final ClienteRepository clienteRepository;

    public void guardarHistorialPrestamo(HistorialPrestamoDTO historialPrestamoDTO) {


        HistorialPrestamo historialPrestamo = HistorialPrestamo.builder()
                .monto(historialPrestamoDTO.getMonto())
                .fechaCreacion(LocalDateTime.now())
                .estado(historialPrestamoDTO.getEstado())
                .prestamo(historialPrestamoDTO.getPrestamo())
                .build();

        historialPrestamoRepository.save(historialPrestamo);
    }

    public ConsultaHistorialPrestamosResponse obtenerHistorialDePrestamosDeCliente(Long clienteId) {
        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NoSuchElementException("El cliente " + clienteId + " no existe"));

        List<Prestamo> prestamos = prestamoRepository.findByClienteId(clienteId);
        List<ConsultaHistorialPrestamosResponse.Historial> historialPrestamos = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            historialPrestamos.add(ConsultaHistorialPrestamosResponse.Historial.builder()
                            .prestamo(prestamo)
                            .historialPrestamos(historialPrestamoRepository.findByPrestamoId(prestamo.getId()))
                    .build());
        }

        return ConsultaHistorialPrestamosResponse.builder()
                .historiales(historialPrestamos)
                .build();
    }

    public ConsultaHistorialPrestamosResponse obtenerUltimos3PrestamosDeCliente(Long clienteId) {
        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NoSuchElementException("El cliente " + clienteId + " no existe"));

        List<Prestamo> prestamos = prestamoRepository.findTop3ByClienteIdOrderByFechaCreacionDesc(clienteId);
        List<ConsultaHistorialPrestamosResponse.Historial> historialPrestamos = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            historialPrestamos.add(ConsultaHistorialPrestamosResponse.Historial.builder()
                    .prestamo(prestamo)
                    .historialPrestamos(historialPrestamoRepository.findByPrestamoId(prestamo.getId()))
                    .build());
        }

        return ConsultaHistorialPrestamosResponse.builder()
                .historiales(historialPrestamos)
                .build();
    }


}
