package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.cliente.ClienteContatosResponseDTO;
import onsales.api.dto.venda.VendaCreateDTO;
import onsales.api.dto.venda.VendaEVendaItensResponseDTO;
import onsales.api.dto.venda.VendaResponseDTO;
import onsales.api.dto.venda.VendaUpdateDTO;
import onsales.api.repository.VendaRepository;
import onsales.api.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> buscar (){
        List<VendaResponseDTO> vendas = vendaService.listar();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var venda = vendaRepository.getReferenceById(id);
        return ResponseEntity.ok( new VendaResponseDTO(venda));
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> criar(@RequestBody @Valid VendaCreateDTO dto){

        VendaResponseDTO venda = vendaService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(venda);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid VendaUpdateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vendaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        vendaService.excluir(id);
    }

    @GetMapping("/buscar-vendaitens/{id}")
    public ResponseEntity<List<VendaEVendaItensResponseDTO>> buscarVendasEVendasItens(@PathVariable UUID id){
        List<VendaEVendaItensResponseDTO> vendasEItens = vendaService.listarVendasEItens(id);
        return ResponseEntity.ok(vendasEItens);
    }
}
