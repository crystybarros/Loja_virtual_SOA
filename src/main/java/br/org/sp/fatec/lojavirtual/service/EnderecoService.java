package br.org.sp.fatec.lojavirtual.service;

import br.org.sp.fatec.lojavirtual.dto.EnderecoFormDto;
import br.org.sp.fatec.lojavirtual.model.Cliente;
import br.org.sp.fatec.lojavirtual.model.Endereco;
import br.org.sp.fatec.lojavirtual.repository.ClienteRepository;
import br.org.sp.fatec.lojavirtual.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoService(
            EnderecoRepository enderecoRepository,
            ClienteRepository clienteRepository
    ) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Endereco cadastrar(EnderecoFormDto dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Endereco endereco = new Endereco();

        endereco.setCep(dto.getCep());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCliente(cliente);

        return enderecoRepository.save(endereco);
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}