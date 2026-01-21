package onsales.api.controller;

import jakarta.validation.Valid;
import onsales.api.dto.usuario.DadosCadastroUsuarioDTO;
import onsales.api.dto.usuario.DadosLoginDTO;
import onsales.api.dto.usuario.DadosRefreshToken;
import onsales.api.dto.usuario.DadosTokenDTO;
import onsales.api.exception.RegraDeNegocioException;
import onsales.api.model.RefreshToken;
import onsales.api.model.Usuario;
import onsales.api.repository.RefreshTokenRepository;
import onsales.api.repository.UsuarioRepository;
import onsales.api.service.TokenService;
import onsales.api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioService usuarioService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository, RefreshTokenRepository refreshTokenRepository,UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<DadosTokenDTO> efetuarLogin(@Valid @RequestBody DadosLoginDTO dados){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(autenticationToken);

        String tokenAcesso = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        String refreshToken = tokenService.gerarRefreshToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenDTO(tokenAcesso, refreshToken));
    }

    @PostMapping("/atualizar-token")
    public ResponseEntity<DadosTokenDTO> atualizarToken(@RequestBody DadosRefreshToken dados) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(dados.refreshToken())
                .orElseThrow(() -> new RegraDeNegocioException("Refresh token inválido"));

        if (refreshToken.isExpirado()) {
            throw new RegraDeNegocioException("Refresh token expirado");
        }

        Usuario usuario = refreshToken.getUsuario();

        String novoAccessToken = tokenService.gerarToken(usuario);
        String novoRefreshToken = tokenService.gerarRefreshToken(usuario);

        return ResponseEntity.ok(new DadosTokenDTO(novoAccessToken, novoRefreshToken));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody DadosCadastroUsuarioDTO dados) {

        usuarioService.criar(dados);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
