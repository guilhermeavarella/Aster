package com.aster.aster_dashboard_backend.controller.api.operacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aster.aster_dashboard_backend.dto.UsaDto;
import com.aster.aster_dashboard_backend.service.UsaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/operacoes/usa")
public class UsaController {

    private UsaService service;

    @Autowired
    public UsaController(UsaService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsaDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody UsaDto dto) {
        service.create(dto);
    }
    
    @DeleteMapping("/{clienteDocumento}/{licencaId}/{usuarioChaveUso}")
    public void delete(@PathVariable String clienteDocumento, @PathVariable String licencaId, @PathVariable String usuarioChaveUso) {
        service.delete(clienteDocumento, licencaId, usuarioChaveUso);
    }
    
}
