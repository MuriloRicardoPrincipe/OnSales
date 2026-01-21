package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.usuario.DadosCadastroUsuarioDTO;
import onsales.api.dto.usuario.UsuarioResponseDTO;
import onsales.api.model.Usuario;
import onsales.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }


    @Transactional
    public void criar(DadosCadastroUsuarioDTO dto){

        Usuario usuario = new Usuario(
                dto.nome(),
                dto.email(),
                passwordEncoder.encode(dto.senha()),
                "USER",
                LocalDateTime.now()

        );

        usuarioRepository.save(usuario);

    }


    @Transactional
    public void excluir(UUID id){
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        usuarioRepository.delete(usuario);
    }
}
