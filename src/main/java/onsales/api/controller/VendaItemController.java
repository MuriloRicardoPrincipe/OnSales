package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.vendaItem.VendaItemCreateDTO;
import onsales.api.dto.vendaItem.VendaItemResponseDTO;
import onsales.api.dto.vendaItem.VendaItemUpdateDTO;
import onsales.api.repository.VendaItemRepository;
import onsales.api.service.VendaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/venda_item")
public class VendaItemController {

    @Autowired
    private VendaItemRepository vendaItemRepository;

    @Autowired
    private VendaItemService vendaItemService;


    @GetMapping
    public ResponseEntity<List<VendaItemResponseDTO>> buscar (){
        List<VendaItemResponseDTO> vendaItem = vendaItemService.listar();
        return ResponseEntity.ok(vendaItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var vendaItem = vendaItemRepository.getReferenceById(id);
        return ResponseEntity.ok( new VendaItemResponseDTO(vendaItem));
    }

    @PostMapping
    public ResponseEntity<VendaItemResponseDTO> criar(@RequestBody @Valid VendaItemCreateDTO dto){

        VendaItemResponseDTO vendaItem = vendaItemService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vendaItem);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid VendaItemUpdateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vendaItemService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        vendaItemService.excluir(id);
    }

}
