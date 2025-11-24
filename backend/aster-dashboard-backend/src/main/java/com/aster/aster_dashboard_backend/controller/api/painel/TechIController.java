package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.PorcentagemDto;
import com.aster.aster_dashboard_backend.dto.QuantidadeDto;
import com.aster.aster_dashboard_backend.dto.VersaoDto;
import com.aster.aster_dashboard_backend.service.AnaliseQualidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painel/i/tech")
public class TechIController {

    private AnaliseQualidadeService analiseQualidadeService;

    @Autowired
    public TechIController(AnaliseQualidadeService analiseQualidadeService) {
        this.analiseQualidadeService = analiseQualidadeService;
    }

    @GetMapping("/porcentagem-status-licencas")
    public List<PorcentagemDto> findPorcentagemStatusLicencas() {
        return analiseQualidadeService.findPorcentagemAtiva();
    }

    @GetMapping("/quantidade-usuarios-produto")
    public List<QuantidadeDto> findQuantidadeUsuariosProduto() {
        return analiseQualidadeService.findUsuariosTotalProduto();
    }

    @GetMapping("/versoes-recentes")
    public List<VersaoDto> findVersoesRecentes() {
        return analiseQualidadeService.findVersoesMaisRecentes();
    }
}
