package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.produto.ProdutoCreateDTO;
import onsales.api.dto.produto.ProdutoResponseDTO;
import onsales.api.dto.produto.ProdutoUpdateDTO;
import onsales.api.repository.ProdutoRepository;
import onsales.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscar (){
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok( new ProdutoResponseDTO(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody @Valid ProdutoCreateDTO dto){

        ProdutoResponseDTO produto = produtoService.criarProduto(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produto);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoUpdateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.atualizarProduto(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        produtoService.excluir(id);
    }
}
