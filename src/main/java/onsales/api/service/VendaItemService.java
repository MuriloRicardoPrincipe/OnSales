package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import onsales.api.dto.venda.VendaResponseDTO;
import onsales.api.dto.vendaItem.VendaItemCreateDTO;
import onsales.api.dto.vendaItem.VendaItemResponseDTO;
import onsales.api.dto.vendaItem.VendaItemUpdateDTO;
import onsales.api.exception.QuantidadeException;
import onsales.api.model.Venda;
import onsales.api.model.VendaItem;
import onsales.api.repository.EstoqueRepository;
import onsales.api.repository.ProdutoRepository;
import onsales.api.repository.VendaItemRepository;
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
public class VendaItemService {

    @Autowired
    private VendaItemRepository vendaItemRepository;

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;


    public List<VendaItemResponseDTO> listar(){
        return vendaItemRepository.findAll().stream().map(VendaItemResponseDTO::new).toList();
    }

    @Transactional
    public VendaItemResponseDTO criar(VendaItemCreateDTO dto){

        var produto = produtoRepository.findById(dto.produtoId()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
        var estoque = estoqueRepository.findByProdutoId(dto.produtoId());
        var venda = vendaRepository.findById(dto.vendaId()).orElseThrow(() -> new EntityNotFoundException("Venda não encontrada!"));

        if (!validaQuantidade(estoque.getQuantidade(), dto.quantidade())) throw new QuantidadeException("Quantidade invalida, validar quantidade em estoque!");

        estoque.RetiradaEstoque(estoque.getQuantidade(), dto.quantidade());

        var total = totalVendaItem(dto.quantidade(), produto.getPreco());

        VendaItem vendaItem = new VendaItem(
                venda,
                produto,
                dto.quantidade(),
                total,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        vendaItemRepository.save(vendaItem);

        var vendaItens = vendaItemRepository.findByVendaId(venda.getId());

        var novoTotal = calcularTotalVenda(vendaItens);

        venda.AtualizaVendaTotal(novoTotal);

        return new VendaItemResponseDTO(
                vendaItem.getId(),
                vendaItem.getVenda().getId(),
                vendaItem.getProduto().getId(),
                vendaItem.getQuantidade(),
                vendaItem.getTotal(),
                vendaItem.getCriacao(),
                vendaItem.getAtualizado()
        );

    }

    @Transactional
    public VendaItemResponseDTO atualizar(UUID id, VendaItemUpdateDTO dto){

        var vendaItem = vendaItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vendas não encontrado!"));
        var produto = produtoRepository.findById(dto.produtoId()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
        var estoque = estoqueRepository.findByProdutoId(dto.produtoId());
        var venda = vendaRepository.findById(vendaItem.getVenda().getId()).orElseThrow(() -> new EntityNotFoundException("Venda não encontrado!"));

        estoque.DevolvendoEstoque(estoque.getQuantidade(), vendaItem.getQuantidade());//devolver antes de retirar, garantir o controler do estoque

        if (!validaQuantidade(estoque.getQuantidade(), dto.quantidade())) throw new QuantidadeException("Quantidade invalida, validar quantidade em estoque!");

        estoque.RetiradaEstoque(estoque.getQuantidade(), dto.quantidade());//retirar antes de retirar, garantir o controler do estoque

        var total = totalVendaItem(dto.quantidade(), produto.getPreco());

        vendaItem.AtualizarVendaItem(
                produto,
                dto.quantidade(),
                total,
                LocalDateTime.now()
        );

        var vendaItens = vendaItemRepository.findByVendaId(venda.getId());

        var novoTotal = calcularTotalVenda(vendaItens);

        venda.AtualizaVendaTotal(novoTotal);

        return new VendaItemResponseDTO(
                vendaItem.getId(),
                vendaItem.getVenda().getId(),
                vendaItem.getProduto().getId(),
                vendaItem.getQuantidade(),
                vendaItem.getTotal(),
                vendaItem.getCriacao(),
                vendaItem.getAtualizado()
        );

    }

    @Transactional
    public void excluir(UUID id) {
        var vendaItem = vendaItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item de venda não encontrada para excluir!"));

        var estoque = estoqueRepository.findByProdutoId(vendaItem.getProduto().getId());

        estoque.DevolvendoEstoque(estoque.getQuantidade(), vendaItem.getQuantidade());//devolver antes de retirar, garantir o controler do estoque

        var venda = vendaRepository.findById(vendaItem.getVenda().getId())
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada!"));

        vendaItemRepository.delete(vendaItem);

        var vendaItens = vendaItemRepository.findByVendaId(venda.getId());
        var novoTotal = calcularTotalVenda(vendaItens);
        venda.AtualizaVendaTotal(novoTotal);
    }



    private boolean validaQuantidade(Integer quantidadeEstoque, @NotNull Integer quantidadeVenda) {
        if (quantidadeEstoque < quantidadeVenda) return false;

        else return true;
    }

    private BigDecimal totalVendaItem(Integer quantidade, BigDecimal preco){
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    private BigDecimal calcularTotalVenda(List<VendaItem> itens) {
        return itens.stream()
                .map(VendaItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
