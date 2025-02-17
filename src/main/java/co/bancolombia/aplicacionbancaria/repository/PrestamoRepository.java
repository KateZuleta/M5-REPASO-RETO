package co.bancolombia.aplicacionbancaria.repository;

import co.bancolombia.aplicacionbancaria.modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository  extends JpaRepository<Prestamo, Long> {
    Optional<Prestamo> findById(Long prestamoId);

    List<Prestamo> findByClienteId(Long clienteId);

    Optional<Prestamo> findByClienteIdAndId(Long clienteId, Long prestamoId);

    List<Prestamo> findTop3ByClienteIdOrderByFechaCreacionDesc(Long clienteId);

}
