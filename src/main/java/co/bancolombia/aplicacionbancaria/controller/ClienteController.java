package co.bancolombia.aplicacionbancaria.controller;

import co.bancolombia.aplicacionbancaria.modelo.DTO.ClienteDTO;
import co.bancolombia.aplicacionbancaria.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // Registrar un nuevo cliente
    @PostMapping("/registrar")
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody ClienteDTO clienteRequest) {
        ClienteDTO cliente = clienteService.crearCliente(clienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

}

