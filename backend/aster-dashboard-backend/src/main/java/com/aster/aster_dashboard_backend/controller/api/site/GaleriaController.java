package com.aster.aster_dashboard_backend.controller.api.site;

import com.aster.aster_dashboard_backend.dto.PacoteDto;
import com.aster.aster_dashboard_backend.dto.ProdutoDto;
import com.aster.aster_dashboard_backend.service.PacoteService;
import com.aster.aster_dashboard_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/site/galeria")
public class GaleriaController {

    private final PacoteService pacoteService;
    private ProdutoService produtoService;

    @Autowired
    public GaleriaController(ProdutoService produtoService, PacoteService pacoteService) {
        this.produtoService = produtoService;
        this.pacoteService = pacoteService;
    }

    @GetMapping("/produtos")
    public List<ProdutoDto> findProdutosComercializaveis() {
        return produtoService.findProdutosComercializaveis();
    }

    @GetMapping("/pacotes")
    public List<PacoteDto> findPacotes() {
        return pacoteService.findAll();
    }
}
