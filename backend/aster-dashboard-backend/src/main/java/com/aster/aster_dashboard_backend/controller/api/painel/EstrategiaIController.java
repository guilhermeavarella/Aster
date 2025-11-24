package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.ClientesPaisDto;
import com.aster.aster_dashboard_backend.dto.EntidadesDto;
import com.aster.aster_dashboard_backend.dto.PorcentagemDto;
import com.aster.aster_dashboard_backend.service.DemografiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painel/i/estrategia")
public class EstrategiaIController {

    DemografiaService demografiaService;

    @Autowired
    public EstrategiaIController(DemografiaService demografiaService) {
        this.demografiaService = demografiaService;
    }

    @GetMapping("/quantidades-entidades")
    public EntidadesDto findQuantidadesEntidades() {
        return demografiaService.getQuantidadesEntidades();
    }

    @GetMapping("/clientes-continente")
    public List<Long> findClientesContinente() {
        return demografiaService.findClientesContinente();
    }

    @GetMapping("/clientes-pais")
    public List<ClientesPaisDto> findClientesPais() {
        return demografiaService.findClientesPais();
    }

    @GetMapping("/porcentagem-segmento-atuacao")
    public List<PorcentagemDto> findPorcentagemSegmentoAtuacao() {
        return demografiaService.findPorcentagemSetorAtuacao();
    }

    @GetMapping("/porcentagem-atividade-uso")
    public List<PorcentagemDto> findPorcentagemAtividadeUso() {
        return demografiaService.findPorcentagemAtividadeUso();
    }
}
