package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.ClienteIndividualDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.ClienteIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operacoes/cliente-individual")
public class ClienteIndividualController {

    private ClienteIndividualService service;

    @Autowired
    public ClienteIndividualController(ClienteIndividualService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<ClienteIndividualDto> findAll(int page) {
        Page<ClienteIndividualDto> pagina = service.findAll(page);
        return new PageResponseDto<>(pagina);
    }
}
