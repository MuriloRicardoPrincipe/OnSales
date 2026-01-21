package onsales.api.seguranca;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import onsales.api.model.Usuario;
import onsales.api.repository.UsuarioRepository;
import onsales.api.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class FiltroTokenAcesso  extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;


    public FiltroTokenAcesso(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarTokenRequisicao(request);

        if (token != null) {
            UUID userId = tokenService.verificarAccessToken(token);

            Usuario usuario = usuarioRepository.findById(userId).orElseThrow();

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }


    private String recuperarTokenRequisicao(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
