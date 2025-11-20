package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.ClienteIndividualDto;
import com.aster.aster_dashboard_backend.dto.ClienteOrganizacaoDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.ClienteOrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/cliente-organizacao")
public class ClienteOrganizacaoController {

    private ClienteOrganizacaoService service;

    @Autowired
    public ClienteOrganizacaoController(ClienteOrganizacaoService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<ClienteOrganizacaoDto> findAllPaginated(@RequestParam int page) {
        Page<ClienteOrganizacaoDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{documento}")
    public ClienteOrganizacaoDto findByDocumento(@PathVariable String documento) {
        return service.findByDocumento(documento);
    }

    @PostMapping
    public void create(@RequestBody ClienteOrganizacaoDto dto) {
        service.create(dto);
    }

    @PatchMapping("/{documento}")
    public void update(@PathVariable String documento, @RequestBody ClienteOrganizacaoDto dto) {
        service.update(documento, dto);
    }

    @DeleteMapping("/{documento}")
    public void delete(@PathVariable String documento) {
        service.delete(documento);
    }

}
