package co.bancolombia.aplicacionbancaria.repository;

import co.bancolombia.aplicacionbancaria.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long clientId);
}
