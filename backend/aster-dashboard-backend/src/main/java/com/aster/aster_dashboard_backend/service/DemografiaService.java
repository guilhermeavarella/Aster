package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.ClientesPaisDto;
import com.aster.aster_dashboard_backend.dto.EntidadesDto;
import com.aster.aster_dashboard_backend.dto.QuantidadeDto;
import com.aster.aster_dashboard_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemografiaService {

    private LicencaRepository licencaRepository;
    private ProdutoRepository produtoRepository;
    private ProdutoVersaoRepository produtoVersaoRepository;
    private ClienteRepository clienteRepository;
    private PacoteRepository pacoteRepository;
    private DevolutivaRepository devolutivaRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public DemografiaService(LicencaRepository licencaRepository, ProdutoRepository produtoRepository, ProdutoVersaoRepository produtoVersaoRepository, ClienteRepository clienteRepository, PacoteRepository pacoteRepository, DevolutivaRepository devolutivaRepository, UsuarioRepository usuarioRepository) {
        this.licencaRepository = licencaRepository;
        this.produtoRepository = produtoRepository;
        this.produtoVersaoRepository = produtoVersaoRepository;
        this.clienteRepository = clienteRepository;
        this.pacoteRepository = pacoteRepository;
        this.devolutivaRepository = devolutivaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public EntidadesDto getQuantidadesEntidades() {
        EntidadesDto dto = new EntidadesDto();

        // Licenças
        QuantidadeDto licencasAtivas = new QuantidadeDto();
        licencasAtivas.setId(0);
        licencasAtivas.setLabel("Ativas");
        licencasAtivas.setValue(licencaRepository.countByAtiva(true));

        QuantidadeDto licencasInativas = new QuantidadeDto();
        licencasInativas.setId(1);
        licencasInativas.setLabel("Inativas");
        licencasInativas.setValue(licencaRepository.countByAtiva(false));

        dto.setLicencas(List.of(licencasAtivas, licencasInativas));

        // Produtos
        QuantidadeDto produtosComercializaveis = new QuantidadeDto();
        produtosComercializaveis.setId(0);
        produtosComercializaveis.setLabel("Comercializável");
        produtosComercializaveis.setValue(produtoRepository.countByStatus("Comercializável"));

        QuantidadeDto produtosEmDesenvolvimento = new QuantidadeDto();
        produtosEmDesenvolvimento.setId(1);
        produtosEmDesenvolvimento.setLabel("Em Desenvolvimento");
        produtosEmDesenvolvimento.setValue(produtoRepository.countByStatus("Em desenvolvimento"));

        QuantidadeDto produtosDescontinuados = new QuantidadeDto();
        produtosDescontinuados.setId(2);
        produtosDescontinuados.setLabel("Descontinuado");
        produtosDescontinuados.setValue(produtoRepository.countByStatus("Descontinuado"));

        dto.setProdutos(List.of(produtosComercializaveis, produtosEmDesenvolvimento, produtosDescontinuados));

        // Versões
        QuantidadeDto versao = new QuantidadeDto();
        versao.setId(0);
        versao.setLabel("Total");
        versao.setValue(produtoVersaoRepository.count());

        dto.setVersoes(List.of(versao));

        // Clientes
        QuantidadeDto cliente =  new QuantidadeDto();
        cliente.setId(0);
        cliente.setLabel("Total");
        cliente.setValue(clienteRepository.count());

        dto.setClientes(List.of(cliente));

        // Pacotes
        QuantidadeDto pacote = new QuantidadeDto();
        pacote.setId(0);
        pacote.setLabel("Total");
        pacote.setValue(pacoteRepository.count());

        dto.setPacotes(List.of(pacote));

        // Devolutivas
        QuantidadeDto devolutiva = new QuantidadeDto();
        devolutiva.setId(0);
        devolutiva.setLabel("Total");
        devolutiva.setValue(devolutivaRepository.count());

        dto.setDevolutivas(List.of(devolutiva));

        // Usuários
        QuantidadeDto usuario = new QuantidadeDto();
        usuario.setId(0);
        usuario.setLabel("Total");
        usuario.setValue(usuarioRepository.count());

        dto.setUsuarios(List.of(usuario));

        return dto;

    }

    public List<Long> findClientesContinente() {
        List<Long> clientes = new ArrayList<>();

        clientes.add(clienteRepository.countByContinente("América do Sul"));
        clientes.add(clienteRepository.countByContinente("América do Norte"));
        clientes.add(clienteRepository.countByContinente("África"));
        clientes.add(clienteRepository.countByContinente("Ásia"));
        clientes.add(clienteRepository.countByContinente("Europa"));
        clientes.add(clienteRepository.countByContinente("Oceania"));

        return clientes;
    }

    public List<ClientesPaisDto> findClientesPais() {
        return clienteRepository.countByRegiao();
    }
}
