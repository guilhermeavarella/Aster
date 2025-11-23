package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.TotalVendasPacoteDto;
import com.aster.aster_dashboard_backend.dto.TotalVendasProdutoDto;
import com.aster.aster_dashboard_backend.dto.VendasMensaisProdutoDto;
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

    @Autowired
    public EstrategiaDController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/total-vendas-produto")
    public List<TotalVendasProdutoDto> findTotalVendasProduto() {
        return produtoService.findTotalVendasProduto();
    }

    @GetMapping("/total-vendas-pacote")
    public List<TotalVendasPacoteDto> findTotalVendasPacote() {
        return produtoService.findTotalVendasPacote();
    }

    @GetMapping("/vendas-mensais-produto")
    public List<VendasMensaisProdutoDto> findVendasMensaisProduto() {
        return produtoService.findVendasMensaisProduto();
    }
}
