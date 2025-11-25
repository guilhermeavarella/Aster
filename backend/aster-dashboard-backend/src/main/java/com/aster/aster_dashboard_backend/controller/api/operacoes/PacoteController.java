package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.PacoteDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/pacote")
public class PacoteController {

    private PacoteService service;

    @Autowired
    public PacoteController(PacoteService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<PacoteDto> findAllPaginated(@RequestParam int page) {
        Page<PacoteDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{nome}")
    public PacoteDto findByNome(@PathVariable String nome) {
        return service.findByNome(nome);
    }

    @PostMapping
    public void create(@RequestBody PacoteDto dto) {
        service.create(dto);
    }

    @PatchMapping("/{nome}")
    public void update(@PathVariable String nome, @RequestBody PacoteDto dto) {
        service.update(nome, dto);
    }

    @DeleteMapping("/{nome}")
    public void delete(@PathVariable String nome) {
        service.delete(nome);
    }
}
