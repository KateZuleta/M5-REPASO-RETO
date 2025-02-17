package co.bancolombia.aplicacionbancaria.modelo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClienteDTO {
    @NotNull(message = "El nombre es obligatorio")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El email es obligatorio")
    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotNull(message = "El teléfono es obligatorio")
    @NotEmpty(message = "El teléfono no puede estar vacío")
    private String telefono;
}
