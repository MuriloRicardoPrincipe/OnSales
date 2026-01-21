package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import onsales.api.dto.cliente.ClienteEnderecoResponseDTO;
import onsales.api.dto.venda.VendaCreateDTO;
import onsales.api.dto.venda.VendaEVendaItensResponseDTO;
import onsales.api.dto.venda.VendaResponseDTO;
import onsales.api.dto.venda.VendaUpdateDTO;
import onsales.api.model.Cliente;
import onsales.api.model.Venda;
import onsales.api.repository.ClienteRepository;
import onsales.api.repository.EstoqueRepository;
import onsales.api.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<VendaResponseDTO> listar(){
        return vendaRepository.findAll().stream().map(VendaResponseDTO::new).toList();
    }

    public VendaResponseDTO criar(VendaCreateDTO dto){

        try{
            var cliente = clienteRepository.findById(dto.clienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));

            Venda venda = new Venda(
                    cliente,
                    BigDecimal.ZERO,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            vendaRepository.save(venda);

            return new VendaResponseDTO(
                    venda.getId(),
                    venda.getCliente().getId(),
                    venda.getTotal(),
                    venda.getCriacao(),
                    venda.getAtualizado());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VendaResponseDTO atualizar(UUID id, VendaUpdateDTO dto){
        var venda = vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada!"));

        var cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        venda.AtualizacaoVenda(
                cliente,
                venda.getAtualizado()
        );

        return new VendaResponseDTO(
                venda.getId(),
                venda.getCliente().getId(),
                venda.getTotal(),
                venda.getCriacao(),
                venda.getAtualizado());
    }

    public void excluir(UUID id) {
        var venda = vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada!"));

        vendaRepository.delete(venda);
    }

    public List<VendaEVendaItensResponseDTO> listarVendasEItens(UUID id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrado"));

        return vendaRepository.findAll().stream().map(VendaEVendaItensResponseDTO::new).toList();
    }

}
