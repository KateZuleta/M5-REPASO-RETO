package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.modelo.DTO.*;
import co.bancolombia.aplicacionbancaria.modelo.enums.EstadoPrestamo;
import co.bancolombia.aplicacionbancaria.modelo.Cliente;
import co.bancolombia.aplicacionbancaria.modelo.Prestamo;
import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaPrestamoResponse;
import co.bancolombia.aplicacionbancaria.modelo.response.ConsultaPrestamosResponse;
import co.bancolombia.aplicacionbancaria.modelo.response.SimularCuotasResponse;
import co.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import co.bancolombia.aplicacionbancaria.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final ClienteRepository clienteRepository;
    private final HistorialPrestamoService historialPrestamoService;

    public String solicitudPrestamo(PrestamoDTO prestamoDTO) {
        Cliente cliente = clienteRepository.findById(prestamoDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("El cliente " + prestamoDTO.getClientId() + " no existe"));

        Prestamo prestamo = Prestamo.builder()
                .cliente(cliente)
                .duracionMeses(prestamoDTO.getDuracionMeses())
                .interes(BigDecimal.ONE)
                .monto(prestamoDTO.getMonto())
                .estado(EstadoPrestamo.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();

        prestamoRepository.save(prestamo);

        HistorialPrestamoDTO historialPrestamoDTO = HistorialPrestamoDTO.builder()
                .estado(prestamoDTO.getEstado())
                .monto(prestamoDTO.getMonto())
                .prestamo(prestamo)
                .build();

        historialPrestamoService.guardarHistorialPrestamo(historialPrestamoDTO);
        return "¡El prestamo se registro con exito! " + cliente.getNombre() + " ya se encuentra en validacion";
    }

    public String gestionarEstadoPrestamo(GestionarPrestamoDTO aprobacionPrestamoDTO) {
        if (aprobacionPrestamoDTO.getEstado().name().equals(EstadoPrestamo.PENDIENTE.name())) {
            throw new InternalError("El estado " + EstadoPrestamo.PENDIENTE.name() + " no esta permitido");
        }

        Prestamo prestamo = prestamoRepository.findById(aprobacionPrestamoDTO.getPrestamoId())
                .orElseThrow(() -> new NoSuchElementException("El prestamo " + aprobacionPrestamoDTO.getPrestamoId() + " no existe"));

        prestamo.setEstado(aprobacionPrestamoDTO.getEstado());
        prestamo.setFechaActualizacion(LocalDateTime.now());

        prestamoRepository.save(prestamo);

        HistorialPrestamoDTO historialPrestamoDTO = HistorialPrestamoDTO.builder()
                .estado(prestamo.getEstado())
                .monto(prestamo.getMonto())
                .prestamo(prestamo)
                .build();

        historialPrestamoService.guardarHistorialPrestamo(historialPrestamoDTO);

        return "¡El prestamo " + aprobacionPrestamoDTO.getPrestamoId() + " se actualizo con exito!";
    }

    public ConsultaPrestamosResponse consultaPrestamos(long clientId){
        clienteRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("El cliente " + clientId + " no existe"));

        List<Prestamo> prestamos = prestamoRepository.findByClienteId(clientId);

        return ConsultaPrestamosResponse.builder()
                .prestamos(prestamos)
                .build();
    }

    public ConsultaPrestamoResponse consultaPrestamo(ConsultaPrestamoDTO consultaPrestamoDTO){
        clienteRepository.findById(consultaPrestamoDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("El cliente " + consultaPrestamoDTO.getClientId() + " no existe"));

        Prestamo prestamo = prestamoRepository.findByClienteIdAndId(consultaPrestamoDTO.getClientId(), consultaPrestamoDTO.getPrestamoId())
                .orElseThrow(() -> new NoSuchElementException("El prestamo " + consultaPrestamoDTO.getPrestamoId() + " no existe"));

        return ConsultaPrestamoResponse.builder()
                .prestamo(prestamo)
                .build();
    }

    public SimularCuotasResponse calcularCuota(SimularCuotasDTO simularCuotasDTO) {
        // Tasa de interés mensual en formato decimal
        BigDecimal tasaInteresMensual = new BigDecimal("0.17"); // 1.5% mensual
        BigDecimal monto = simularCuotasDTO.getMonto();
        int duracionMeses = simularCuotasDTO.getDuracionMeses();
        BigDecimal CuotaMensualSinInt =  monto.divide(BigDecimal.valueOf(simularCuotasDTO.getDuracionMeses()),RoundingMode.HALF_UP);
        List<BigDecimal> cuotasMensuales = new ArrayList<>();
        for (int i = 1; i <= duracionMeses; i++) {
            BigDecimal interesTotal = monto.multiply(tasaInteresMensual);
            BigDecimal TotalCuota = CuotaMensualSinInt.add(interesTotal);
            cuotasMensuales.add(TotalCuota);
            monto.subtract(CuotaMensualSinInt);
            CuotaMensualSinInt = monto.divide(BigDecimal.valueOf(simularCuotasDTO.getDuracionMeses()),RoundingMode.HALF_UP);
        }

        return SimularCuotasResponse.builder()
                .interes(tasaInteresMensual)
                .monto(simularCuotasDTO.getMonto())
                .duracionPrestamo(duracionMeses)
                .cuotasMensuales(cuotasMensuales)
                .build();
    }
}
