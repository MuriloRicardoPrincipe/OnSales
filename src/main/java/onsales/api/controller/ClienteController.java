package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.cliente.*;
import onsales.api.repository.ClienteRepository;
import onsales.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

    @RestController
    @RequestMapping("/cliente")
    public class ClienteController {

        @Autowired
        private ClienteService clienteService;

        @Autowired
        private ClienteRepository clienteRepository;

        @GetMapping
        public ResponseEntity<List<ClienteResponseDTO>> buscar(){
            List<ClienteResponseDTO> clientes = clienteService.listarClientes();
            return ResponseEntity.ok(clientes);
        }

        @GetMapping("/{id}")
        public ResponseEntity detalhe(@PathVariable UUID id){
            var cliente = clienteRepository.getReferenceById(id);
            return ResponseEntity.ok( new ClienteResponseDTO(cliente));
        }

        @PostMapping
        public ResponseEntity<ClienteResponseDTO> criar(
                @RequestBody @Valid ClienteCreateDTO dto) {

            ClienteResponseDTO clienteCriado = clienteService.CadastrarCliente(dto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(clienteCriado);
        }

        @PutMapping("/{id}")
        public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid ClienteUpdateDTO dto) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(clienteService.atualizar(id, dto));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void excluir(@PathVariable UUID id) {
            clienteService.excluir(id);
        }

        //consulta para exibir contatos de um cliente.
        @GetMapping("/buscar-contatos/{id}")
        public ResponseEntity<List<ClienteContatosResponseDTO>> buscarContatos(@PathVariable UUID id){
            List<ClienteContatosResponseDTO> clientes = clienteService.listarClienteESeusContatos(id);
            return ResponseEntity.ok(clientes);
        }

        @GetMapping("/buscar-enderecos/{id}")
        public ResponseEntity<List<ClienteEnderecoResponseDTO>> buscarEnderecos(@PathVariable UUID id){
            List<ClienteEnderecoResponseDTO> clientes = clienteService.listarClienteESeusEnderecos(id);
            return ResponseEntity.ok(clientes);
        }

    }