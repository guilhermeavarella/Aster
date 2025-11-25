package com.aster.aster_dashboard_backend.controller.api.operacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aster.aster_dashboard_backend.dto.ContemDto;
import com.aster.aster_dashboard_backend.service.ContemService;

@RestController
@RequestMapping("/api/operacoes/contem")
public class ContemController {
    
    private ContemService service;

    @Autowired
    public ContemController(ContemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContemDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody ContemDto dto) {
        service.create(dto);
    }

    @DeleteMapping("/{pacoteNome}/{produtoId}")
    public void delete(@PathVariable String pacoteNome, @PathVariable String produtoId) {
        service.delete(pacoteNome, produtoId);
    }
}
