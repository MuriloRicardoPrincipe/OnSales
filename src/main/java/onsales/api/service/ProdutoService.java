package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.produto.ProdutoCreateDTO;
import onsales.api.dto.produto.ProdutoResponseDTO;
import onsales.api.dto.produto.ProdutoUpdateDTO;
import onsales.api.model.Produto;
import onsales.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoResponseDTO> listarProdutos(){
        return produtoRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    public ProdutoResponseDTO criarProduto(ProdutoCreateDTO dto){

        Produto produto = new Produto(
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                dto.preco(),
                dto.nome(),
                dto.descricao()
        );

        produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getAtivo(),
                produto.getCriacao(),
                produto.getAtualizado()
        );
    }

    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoUpdateDTO dto){

        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não localizado!"));

        produto.AtualizacaoProduto(LocalDateTime.now(), dto.preco(),dto.nome(),dto.descricao());

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getAtivo(),
                produto.getCriacao(),
                produto.getAtualizado()
        );
    }

    public void excluirProdutoDinamico(UUID id){
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.DesativarProduto(false);
    }

    public void excluir(UUID id){
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

}
