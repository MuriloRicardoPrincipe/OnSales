package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.contato.ContatoCreateDTO;
import onsales.api.dto.contato.ContatoResponseDTO;
import onsales.api.dto.contato.ContatoUpdateDTO;
import onsales.api.repository.ContatoRepository;
import onsales.api.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> buscar (){
        List<ContatoResponseDTO> contatos = contatoService.listaContato();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var contato = contatoRepository.getReferenceById(id);
        return ResponseEntity.ok( new ContatoResponseDTO(contato));
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> criar(@RequestBody @Valid ContatoCreateDTO dto){

        ContatoResponseDTO contatoCriado = contatoService.cadastrarContato(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contatoCriado);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @RequestBody @Valid ContatoUpdateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contatoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        contatoService.excluir(id);
    }

}
