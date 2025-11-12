package com.aster.aster_dashboard_backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponseDto<T> {

    private int page_number;
    private long total_registros;
    private List<T> content;

    public PageResponseDto(Page<T> page) {
        this.page_number = page.getNumber();
        this.total_registros = page.getTotalElements();
        this.content = page.getContent();
    }
}
