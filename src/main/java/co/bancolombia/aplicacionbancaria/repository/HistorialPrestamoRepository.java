package co.bancolombia.aplicacionbancaria.repository;

import co.bancolombia.aplicacionbancaria.modelo.HistorialPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialPrestamoRepository extends JpaRepository<HistorialPrestamo, Long> {
    List<HistorialPrestamo> findByPrestamoId(Long prestamoId);
}
