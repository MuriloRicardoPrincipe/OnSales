package onsales.api.service;

import jakarta.persistence.EntityNotFoundException;
import onsales.api.dto.endereco.EnderecoCreateDTO;
import onsales.api.dto.endereco.EnderecoResponseDTO;
import onsales.api.dto.endereco.EnderecoUpdateDTO;
import onsales.api.model.Endereco;
import onsales.api.repository.ClienteRepository;
import onsales.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<EnderecoResponseDTO> listaEnderecos(){
        return enderecoRepository.findAll().stream().map(EnderecoResponseDTO::new).toList();
    }

    public EnderecoResponseDTO cadastrarEndereco(EnderecoCreateDTO dto){
        var cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Endereco endereco =  new Endereco(
                dto.rua(),
                dto.numero(),
                dto.complemento(),
                dto.bairro(),
                dto.cidade(),
                dto.estado(),
                dto.cep(),
                cliente);

        enderecoRepository.save(endereco);

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getCliente().getId()
        );

    }

    public EnderecoResponseDTO atualizar(UUID id , EnderecoUpdateDTO dto){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        endereco.AtualizacaoEndereco(
                dto.rua(),
                dto.numero(),
                dto.complemento(),
                dto.bairro(),
                dto.cidade(),
                dto.estado(),
                dto.cep());

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getCliente().getId()
        );
    }

    public void excuir (UUID id){
        var endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        enderecoRepository.delete(endereco);
    }

}
