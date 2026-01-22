package onsales.api.service;

import onsales.api.model.TipoDocumento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.cliente.*;
import onsales.api.model.Cliente;
import onsales.api.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(
                "Murilo",
                TipoDocumento.CPF,
                "12345678900",
                LocalDateTime.now()
        );
    }

    // ======================
    // LISTAR CLIENTES
    // ======================
    @Test
    void listarClientes_deveRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteResponseDTO> resultado = clienteService.listarClientes();

        assertEquals(1, resultado.size());
        assertEquals("Murilo", resultado.get(0).nome());
        verify(clienteRepository).findAll();
    }

    // ======================
    // CADASTRAR CLIENTE
    // ======================
    @Test
    void cadastrarCliente_deveSalvarERetornarDTO() {
        ClienteCreateDTO dto = new ClienteCreateDTO(
                "Murilo",
                TipoDocumento.CPF,
                "12345678900",
                LocalDateTime.now()
        );

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        ClienteResponseDTO response = clienteService.CadastrarCliente(dto);

        assertNotNull(response);
        assertEquals("Murilo", response.nome());
        assertEquals(TipoDocumento.CPF, response.tipoDocumento());
        verify(clienteRepository).save(any(Cliente.class));
    }

    // ======================
    // ATUALIZAR CLIENTE
    // ======================
    @Test
    void atualizar_deveAtualizarClienteExistente() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        ClienteUpdateDTO dto = new ClienteUpdateDTO(
                "Novo Nome",
                TipoDocumento.CNPJ,
                "999999999"
        );

        ClienteResponseDTO response = clienteService.atualizar(id, dto);

        assertEquals("Novo Nome", response.nome());
        assertEquals(TipoDocumento.CNPJ, response.tipoDocumento());
    }

    @Test
    void atualizar_deveLancarExcecaoQuandoClienteNaoExiste() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> clienteService.atualizar(id, new ClienteUpdateDTO("x",TipoDocumento.CPF,"z")));
    }

    // ======================
    // EXCLUIR CLIENTE
    // ======================
    @Test
    void excluir_deveRemoverClienteExistente() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        clienteService.excluir(id);

        verify(clienteRepository).delete(cliente);
    }

    @Test
    void excluir_deveLancarExcecaoQuandoClienteNaoExiste() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> clienteService.excluir(id));
    }

    // ======================
    // CLIENTE + CONTATOS
    // ======================
    @Test
    void listarClienteESeusContatos_deveRetornarContatosDoCliente() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        when(clienteRepository.findAll())
                .thenReturn(List.of(cliente));

        List<ClienteContatosResponseDTO> response =
                clienteService.listarClienteESeusContatos(id);

        assertFalse(response.isEmpty());
    }

    // ======================
    // CLIENTE + ENDEREÇOS
    // ======================
    @Test
    void listarClienteESeusEnderecos_deveRetornarEnderecosDoCliente() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        when(clienteRepository.findAll())
                .thenReturn(List.of(cliente));

        List<ClienteEnderecoResponseDTO> response =
                clienteService.listarClienteESeusEnderecos(id);

        assertFalse(response.isEmpty());
    }
}
