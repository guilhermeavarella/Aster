package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.ClienteIndividualDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.ClienteIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/cliente-individual")
public class ClienteIndividualController {

    private ClienteIndividualService service;

    @Autowired
    public ClienteIndividualController(ClienteIndividualService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<ClienteIndividualDto> findAll(@RequestParam int page) {
        Page<ClienteIndividualDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{documento}")
    public ClienteIndividualDto findByDocumento(@PathVariable String documento) {

        return service.findByDocumento(documento);
    }

    @PostMapping
    public void create(@RequestBody ClienteIndividualDto dto) {
        service.create(dto);
    }

    @PatchMapping("/{documento}")
    public void update(@PathVariable String documento, @RequestBody ClienteIndividualDto dto) {
        service.update(documento, dto);
    }

    @DeleteMapping("/{documento}")
    public void delete(@PathVariable String documento) {
        service.delete(documento);
    }
}
