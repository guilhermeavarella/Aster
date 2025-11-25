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
@RequestMapping("/api/site/home/")
public class HomeController {

    private ProdutoService produtoService;
    private PacoteService pacoteService;

    @Autowired
    public HomeController(ProdutoService produtoService, PacoteService pacoteService) {
        this.produtoService = produtoService;
        this.pacoteService = pacoteService;
    }

    @GetMapping("/produtos-mais-populares")
    public List<ProdutoDto> findProdutosMaisPopulares() {
        return produtoService.findProdutosMaisPopulares();
    }

    @GetMapping("/pacotes-mais-populares")
    public List<PacoteDto> findPacotesMaisPopulares() {
        return pacoteService.findPacotesMaisPopulares();
    }
}
