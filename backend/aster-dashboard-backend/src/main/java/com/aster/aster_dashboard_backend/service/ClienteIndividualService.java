package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ClienteIndividualConverter;
import com.aster.aster_dashboard_backend.dto.ClienteIndividualDto;
import com.aster.aster_dashboard_backend.entity.Individual;
import com.aster.aster_dashboard_backend.repository.ClienteIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteIndividualService {

    private final ClienteIndividualRepository repository;
    private ClienteIndividualConverter converter;

    @Autowired
    public ClienteIndividualService(ClienteIndividualRepository repository, ClienteIndividualConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<ClienteIndividualDto> findAll(int page) {
        Page<Individual> clientesIndividuais = repository.findAll(PageRequest.of(page, 15));
        return clientesIndividuais.map(converter::toDto);
    }


}
