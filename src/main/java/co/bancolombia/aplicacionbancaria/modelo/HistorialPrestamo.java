package co.bancolombia.aplicacionbancaria.modelo;

import co.bancolombia.aplicacionbancaria.modelo.enums.EstadoPrestamo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "historial_prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class HistorialPrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;
    private BigDecimal monto;
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;
    @Column(name = "fecha_creacion")
    @JsonProperty("fecha_creacion")
    private LocalDateTime fechaCreacion;
}
