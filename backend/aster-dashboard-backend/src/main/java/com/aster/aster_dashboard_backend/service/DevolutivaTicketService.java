package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.DevolutivaTicketConverter;
import com.aster.aster_dashboard_backend.dto.DevolutivaTicketDto;
import com.aster.aster_dashboard_backend.entity.Ticket;
import com.aster.aster_dashboard_backend.repository.DevolutivaTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DevolutivaTicketService {

    private DevolutivaTicketRepository repository;
    private DevolutivaTicketConverter converter;

    @Autowired
    public DevolutivaTicketService(DevolutivaTicketRepository repository, DevolutivaTicketConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<DevolutivaTicketDto> findAllPaginated(int page) {
        Page<Ticket> tickets = repository.findAll(PageRequest.of(page, 15));
        return tickets.map(converter::toDto);
    }

    public DevolutivaTicketDto findById(String id) {
        Optional<Ticket> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(DevolutivaTicketDto dto) {

        if (dto.getId() == null) {
            throw new IllegalArgumentException("Um ID deve ser fornecido!");
        }

        if (repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Esse ID já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String id, DevolutivaTicketDto dto) {
        Optional<Ticket> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        Ticket entity = result.get();

        if (dto.getAssunto() != null) {
            entity.setAssunto(dto.getAssunto());
        }

        if (dto.getDataEnvio() != null) {
            entity.setDataEnvio(dto.getDataEnvio());
        }

        if (dto.getMensagem() != null) {
            entity.setMensagem(dto.getMensagem());
        }

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        if (dto.getResposta() != null) {
            entity.setResposta(dto.getResposta());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String id) {
        Optional<Ticket> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        repository.delete(result.get());
    }
}
