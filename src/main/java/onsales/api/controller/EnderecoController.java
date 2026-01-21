package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.endereco.EnderecoCreateDTO;
import onsales.api.dto.endereco.EnderecoResponseDTO;
import onsales.api.dto.endereco.EnderecoUpdateDTO;
import onsales.api.repository.EnderecoRepository;
import onsales.api.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> busca(){
        List<EnderecoResponseDTO> enderecos = enderecoService.listaEnderecos();
        return ResponseEntity.ok(enderecos);
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var endereco = enderecoRepository.getReferenceById(id);
        return ResponseEntity.ok( new EnderecoResponseDTO(endereco));
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> criar(
            @RequestBody @Valid EnderecoCreateDTO dto) {

        EnderecoResponseDTO endereco = enderecoService.cadastrarEndereco(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid EnderecoUpdateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(enderecoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        enderecoService.excuir(id);
    }

}
