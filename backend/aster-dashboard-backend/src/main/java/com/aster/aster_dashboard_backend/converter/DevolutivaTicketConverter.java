package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.DevolutivaTicketDto;
import com.aster.aster_dashboard_backend.entity.Individual;
import com.aster.aster_dashboard_backend.entity.Organizacao;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.Ticket;
import com.aster.aster_dashboard_backend.repository.ClienteIndividualRepository;
import com.aster.aster_dashboard_backend.repository.ClienteOrganizacaoRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DevolutivaTicketConverter {

    private ProdutoRepository produtoRepository;
    private ClienteOrganizacaoRepository clienteOrganizacaoRepository;
    private ClienteIndividualRepository clienteIndividualRepository;

    @Autowired
    public DevolutivaTicketConverter(ProdutoRepository produtoRepository, ClienteOrganizacaoRepository clienteOrganizacaoRepository, ClienteIndividualRepository clienteIndividualRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteOrganizacaoRepository = clienteOrganizacaoRepository;
        this.clienteIndividualRepository = clienteIndividualRepository;
    }

    public DevolutivaTicketDto toDto(Ticket entity) {
        DevolutivaTicketDto dto = new DevolutivaTicketDto();

        return dto.builder()
                .id(entity.getId())
                .assunto(entity.getAssunto())
                .dataEnvio(entity.getDataEnvio())
                .mensagem(entity.getMensagem())
                .produtoId(entity.getProduto().getId())
                .clienteDocumento(entity.getCliente().getDocumento())
                .status(entity.getStatus())
                .resposta(entity.getResposta())
                .build();
    }

    public Ticket toEntity(DevolutivaTicketDto dto) {
        Ticket entity = new Ticket();

        entity.setId(dto.getId());
        entity.setAssunto(dto.getAssunto());
        entity.setDataEnvio(dto.getDataEnvio());
        entity.setMensagem(dto.getMensagem());

        Optional<Produto> produto = produtoRepository.findById(dto.getProdutoId());
        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Não há um produto com esse ID!");
        }

        entity.setProduto(produto.get());

        Optional<Individual> clienteIndividual = clienteIndividualRepository.findById(dto.getClienteDocumento());

        clienteIndividual.ifPresent(entity::setCliente);

        if (clienteIndividual.isEmpty()) {
            Optional<Organizacao> clienteOrganizacao = clienteOrganizacaoRepository.findById(dto.getClienteDocumento());
            if (clienteOrganizacao.isPresent()) {
                entity.setCliente(clienteOrganizacao.get());
            }
            else {
                throw new IllegalArgumentException("Não há um cliente com esse documento!");
            }
        }

        entity.setStatus(dto.getStatus());
        entity.setResposta(dto.getResposta());

        return entity;
    }
}
