package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.service.PacoteService;
import com.aster.aster_dashboard_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painel/d/estrategia")
public class EstrategiaDController {

    private ProdutoService produtoService;
    private PacoteService pacoteService;

    @Autowired
    public EstrategiaDController(ProdutoService produtoService, PacoteService pacoteService) {
        this.produtoService = produtoService;
        this.pacoteService = pacoteService;
    }

    @GetMapping("/total-vendas-produto")
    public List<TotalVendasProdutoDto> findTotalVendasProduto() {
        return produtoService.findTotalVendasProduto();
    }

    @GetMapping("/total-vendas-pacote")
    public List<TotalVendasPacoteDto> findTotalVendasPacote() {
        return pacoteService.findTotalVendasPacote();
    }

    @GetMapping("/vendas-mensais-produto")
    public MensalProdutoDto<DataVendasDto> findVendasMensaisProduto() {
        return produtoService.findVendasMensaisProduto();
    }

    @GetMapping("/vendas-mensais-pacote")
    public MensalPacoteDto<DataVendasDto> findVendasMensaisPacote() {
        return pacoteService.findVendasMensaisPacote();
    }
}
