package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.estoque.EstoqueCreateDTO;
import onsales.api.dto.estoque.EstoqueResponseDTO;
import onsales.api.dto.estoque.EstoqueUpdateDTO;
import onsales.api.repository.EstoqueRepository;
import onsales.api.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> buscar (){
        List<EstoqueResponseDTO> estoque = estoqueService.listarEstoque();
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var estoque = estoqueRepository.getReferenceById(id);
        return ResponseEntity.ok( new EstoqueResponseDTO(estoque));
    }

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> criar(@RequestBody @Valid EstoqueCreateDTO dto){

        EstoqueResponseDTO estoque = estoqueService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estoque);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid EstoqueUpdateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estoqueService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        estoqueService.excluir(id);
    }
}
