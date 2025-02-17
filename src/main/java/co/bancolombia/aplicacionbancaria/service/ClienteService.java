package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.modelo.Cliente;
import co.bancolombia.aplicacionbancaria.modelo.DTO.ClienteDTO;
import co.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = Cliente.builder()
                .email(clienteDTO.getEmail())
                .nombre(clienteDTO.getNombre())
                .telefono(clienteDTO.getTelefono())
                .build();

        clienteRepository.save(cliente);

        return clienteDTO;
    }

}
