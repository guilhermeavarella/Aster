package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.dto.ProdutoVersaoDto;
import com.aster.aster_dashboard_backend.service.ProdutoVersaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/versao")
public class ProdutoVersaoController {

    private ProdutoVersaoService service;

    @Autowired
    public ProdutoVersaoController(ProdutoVersaoService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<ProdutoVersaoDto> findAllPaginated(@RequestParam int page) {
        Page<ProdutoVersaoDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{numeroVersao}/{produtoId}")
    public ProdutoVersaoDto findById(@PathVariable String numeroVersao, @PathVariable String produtoId) {
        return service.findById(numeroVersao, produtoId);
    }

    @PostMapping
    public void create(@RequestBody ProdutoVersaoDto dto) {
        service.create(dto);
    }

    @PatchMapping("/{numeroVersao}/{produtoId}")
    public void update(@PathVariable String numeroVersao, @PathVariable String produtoId, @RequestBody ProdutoVersaoDto dto) {
        service.update(numeroVersao, produtoId, dto);
    }

    @DeleteMapping("/{numeroVersao}/{produtoId}")
    public void delete(@PathVariable String numeroVersao, @PathVariable String produtoId) {
        service.delete(numeroVersao, produtoId);
    }

}
