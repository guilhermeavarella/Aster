package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.ReceitaTotalProdutoDto;
import com.aster.aster_dashboard_backend.service.PacoteService;
import com.aster.aster_dashboard_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painel/d/financas")
public class FinancasDController {

    private ProdutoService produtoService;
    private PacoteService pacoteService;

    @Autowired
    public FinancasDController(ProdutoService produtoService, PacoteService pacoteService) {
        this.produtoService = produtoService;
        this.pacoteService = pacoteService;
    }

    @GetMapping("/receita-total-produto")
    public List<ReceitaTotalProdutoDto> findReceitaTotalProduto() {
        return produtoService.findReceitaTotalProduto();
    }
}
