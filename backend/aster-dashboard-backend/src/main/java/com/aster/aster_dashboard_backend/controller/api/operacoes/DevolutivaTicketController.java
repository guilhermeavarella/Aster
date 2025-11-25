package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.DevolutivaTicketDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.DevolutivaTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/devolutiva-ticket")
public class DevolutivaTicketController {

    private DevolutivaTicketService service;

    @Autowired
    public DevolutivaTicketController(DevolutivaTicketService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<DevolutivaTicketDto> findAllPaginated(@RequestParam int page) {
        Page<DevolutivaTicketDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/nao-respondidos")
    public PageResponseDto<DevolutivaTicketDto> findNaoRespondidos(@RequestParam int page) {
        Page<DevolutivaTicketDto> pagina = service.findNaoRespondidos(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{id}")
    public DevolutivaTicketDto findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody DevolutivaTicketDto dto) {
        service.create(dto);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody DevolutivaTicketDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
