package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.AdquireDto;
import com.aster.aster_dashboard_backend.service.AdquireService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/adquire")
public class AdquireController {

    private AdquireService service;

    @Autowired
    public AdquireController(AdquireService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdquireDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody AdquireDto dto) {
        service.create(dto);
    }
    
    @DeleteMapping("/{clienteDocumento}/{licencaId}/{pacoteNome}")
    public void delete(@PathVariable String clienteDocumento, @PathVariable String licencaId, @PathVariable String pacoteNome) {
        service.delete(clienteDocumento, licencaId, pacoteNome);
    }
}


