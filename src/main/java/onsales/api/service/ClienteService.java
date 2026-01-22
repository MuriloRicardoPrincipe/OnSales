package onsales.api.service;


import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.cliente.*;
import onsales.api.model.Cliente;
import onsales.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> listarClientes(){
        return clienteRepository.findAll().stream().map(ClienteResponseDTO::new).toList();
    }

    public ClienteResponseDTO CadastrarCliente(ClienteCreateDTO dto){

        Cliente cliente = new Cliente(
                dto.nome(),
                dto.tipoDocumento(),
                dto.documento(),
                LocalDateTime.now()
        );

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getTipoDocumento(),
                clienteSalvo.getDocumento(),
                clienteSalvo.getCriacao()
        );
    }


    public ClienteResponseDTO atualizar( UUID id, ClienteUpdateDTO dto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        cliente.atualizarDados(dto.nome(), dto.tipoDocumento(), dto.documento());

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTipoDocumento(),
                cliente.getDocumento(),
                cliente.getCriacao()
        );
    }

    public void excluir(UUID id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        clienteRepository.delete(cliente);

    }

    public List<ClienteContatosResponseDTO> listarClienteESeusContatos(UUID id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return List.of(new ClienteContatosResponseDTO(cliente));
    }

    public List<ClienteEnderecoResponseDTO> listarClienteESeusEnderecos(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return List.of(new ClienteEnderecoResponseDTO(cliente));
    }
}

