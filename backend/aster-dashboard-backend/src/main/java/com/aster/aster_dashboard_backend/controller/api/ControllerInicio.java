package com.aster.aster_dashboard_backend.controller.api;

import com.aster.aster_dashboard_backend.dto.ProdutoInicioDto;
import com.aster.aster_dashboard_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inicio")
public class ControllerInicio {

    private ProdutoService service;

    @Autowired
    public ControllerInicio(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/produtos-geral")
    public List<ProdutoInicioDto> findProdutosInicio() {
        return service.findProdutosInicio();
    }
}
