package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.LicencaDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.LicencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/licenca")
public class LicencaController {

    private LicencaService service;

    @Autowired
    public LicencaController(LicencaService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<LicencaDto> findAllPaginated(@RequestParam int page) {
        Page<LicencaDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{id}")
    public LicencaDto findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody LicencaDto licencaDto) {
        service.create(licencaDto);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody LicencaDto licencaDto) {
        service.update(id, licencaDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
