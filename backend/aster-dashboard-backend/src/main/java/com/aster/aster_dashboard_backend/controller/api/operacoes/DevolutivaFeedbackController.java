package com.aster.aster_dashboard_backend.controller.api.operacoes;

import com.aster.aster_dashboard_backend.dto.DevolutivaFeedbackDto;
import com.aster.aster_dashboard_backend.dto.PageResponseDto;
import com.aster.aster_dashboard_backend.service.DevolutivaFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes/devolutiva-feedback")
public class DevolutivaFeedbackController {

    private DevolutivaFeedbackService service;

    @Autowired
    public DevolutivaFeedbackController(DevolutivaFeedbackService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<DevolutivaFeedbackDto> findAllPaginated(@RequestParam int page) {
        Page<DevolutivaFeedbackDto> pagina = service.findAllPaginated(page);
        return new PageResponseDto<>(pagina);
    }

    @GetMapping("/{id}")
    public DevolutivaFeedbackDto findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody DevolutivaFeedbackDto dto) {
        service.create(dto);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody DevolutivaFeedbackDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
