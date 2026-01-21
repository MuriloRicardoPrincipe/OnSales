package onsales.api.controller;

import onsales.api.dto.usuario.UsuarioResponseDTO;
import onsales.api.repository.UsuarioRepository;
import onsales.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscar(){
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable UUID id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        usuarioService.excluir(id);
    }

}
