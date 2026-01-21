package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.contato.ContatoCreateDTO;
import onsales.api.dto.contato.ContatoResponseDTO;
import onsales.api.dto.contato.ContatoUpdateDTO;
import onsales.api.model.Contato;
import onsales.api.repository.ClienteRepository;
import onsales.api.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<ContatoResponseDTO> listaContato(){
        return contatoRepository.findAll().stream().map(ContatoResponseDTO::new).toList();
    }

    public ContatoResponseDTO cadastrarContato(ContatoCreateDTO dto){

        var cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Contato contato = new Contato(
                cliente,
                dto.telefone(),
                dto.email(),
                dto.celular()
        );

        contatoRepository.save(contato);

        return new ContatoResponseDTO(
                contato.getId(),
                contato.getCliente().getId(),
                contato.getTelefone(),
                contato.getEmail(),
                contato.getCelular()
        );
    }


    public Contato atualizar(UUID id, ContatoUpdateDTO dto){

        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));

        contato.atualizarContato(dto.telefone(), dto.email(), dto.celular());

        return contato;
    }

    public void excluir (UUID id){
        var contato = contatoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));

        contatoRepository.delete(contato);
    }
}
