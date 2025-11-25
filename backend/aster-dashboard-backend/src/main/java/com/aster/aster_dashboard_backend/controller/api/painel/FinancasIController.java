package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.ReceitaTotalDto;
import com.aster.aster_dashboard_backend.dto.TicketMedioClienteDto;
import com.aster.aster_dashboard_backend.service.FluxoCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painel/i/financas")
public class FinancasIController {

    private FluxoCaixaService fluxoCaixaService;

    @Autowired
    public FinancasIController(FluxoCaixaService fluxoCaixaService) {
        this.fluxoCaixaService = fluxoCaixaService;
    }

    @GetMapping("/receita-total-mensal")
    public List<ReceitaTotalDto> findReceitaTotal() {
        return fluxoCaixaService.findReceitaTotal();
    }

    @GetMapping("/ticket-medio-cliente")
    public List<TicketMedioClienteDto> findTicketMedioCliente() {
        return fluxoCaixaService.findTicketMedio();
    }
}
