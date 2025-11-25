package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.DevolutivaFeedbackConverter;
import com.aster.aster_dashboard_backend.dto.DevolutivaFeedbackDto;
import com.aster.aster_dashboard_backend.entity.Feedback;
import com.aster.aster_dashboard_backend.repository.DevolutivaFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DevolutivaFeedbackService {

    private DevolutivaFeedbackRepository repository;
    private DevolutivaFeedbackConverter converter;

    @Autowired
    public DevolutivaFeedbackService(DevolutivaFeedbackRepository repository, DevolutivaFeedbackConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<DevolutivaFeedbackDto> findAllPaginated(int page) {
        Page<Feedback> feedbacks = repository.findAll(PageRequest.of(page, 15));
        return feedbacks.map(converter::toDto);
    }

    public DevolutivaFeedbackDto findById(String id) {
        Optional<Feedback> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(DevolutivaFeedbackDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Um ID deve ser fornecido!");
        }

        if (repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Esse ID já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String id, DevolutivaFeedbackDto dto) {
        Optional<Feedback> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        Feedback entity = result.get();

        if (dto.getAssunto() != null) {
            entity.setAssunto(dto.getAssunto());
        }

        if (dto.getDataEnvio() != null) {
            entity.setDataEnvio(dto.getDataEnvio());
        }

        if (dto.getMensagem() != null) {
            entity.setMensagem(dto.getMensagem());
        }

        if (dto.getAvaliacao() != null) {
            entity.setAvaliacao(dto.getAvaliacao());
        }

        if (dto.getAtividadeUso() != null) {
            entity.setAtividadeUso(dto.getAtividadeUso());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String id) {
        Optional<Feedback> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        repository.delete(result.get());
    }
}
