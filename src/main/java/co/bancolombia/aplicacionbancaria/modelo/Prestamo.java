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
import java.util.List;

@Entity
@Table(name = "prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal monto;
    private BigDecimal interes;
    @Column(name = "duracion_meses")
    @JsonProperty("duracion_meses")
    private int duracionMeses;
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @JsonIgnore
    @OneToMany(mappedBy = "prestamo")
    private List<HistorialPrestamo> historialPrestamos;
    @Column(name = "fecha_creacion")
    @JsonProperty("fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @JsonProperty("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
