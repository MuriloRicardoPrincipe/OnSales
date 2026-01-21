package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.estoque.EstoqueCreateDTO;
import onsales.api.dto.estoque.EstoqueResponseDTO;
import onsales.api.dto.estoque.EstoqueUpdateDTO;
import onsales.api.exception.QuantidadeException;
import onsales.api.model.Estoque;
import onsales.api.repository.EstoqueRepository;
import onsales.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<EstoqueResponseDTO> listarEstoque(){
        return estoqueRepository.findAll().stream().map(EstoqueResponseDTO::new).toList();
    }

    public EstoqueResponseDTO criar(EstoqueCreateDTO dto){

        var produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if(dto.quantidade() < 0){
            throw new QuantidadeException("Quantidade invalida, validar quantidade em estoque!");
        }

        Estoque estoque = new Estoque(
                produto,
                dto.quantidade(),
                LocalDateTime.now()
        );

        estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getQuantidade(),
                estoque.getCriacao(),
                estoque.getAtualizado()
        );
    }

    public EstoqueResponseDTO atualizar(UUID id, EstoqueUpdateDTO dto){

        var estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));

        var produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        estoque.AtualizacaoEstoque(
                produto,
                dto.quantidade(),
                LocalDateTime.now()
        );

        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getQuantidade(),
                estoque.getCriacao(),
                estoque.getAtualizado()
        );
    }

    public void excluir(UUID id) {
        var estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));
        estoqueRepository.delete(estoque);

    }


}
