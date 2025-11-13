package com.aster.aster_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {

    private int pageNumber;
    private long totalEntries;
    private int lastPage;
    private List<T> content;

    public PageResponseDto(Page<T> page) {
        this.pageNumber = page.getNumber();
        this.totalEntries = page.getTotalElements();
        if (page.getTotalPages() == 0) {
            this.lastPage = 0;
        } else {
            this.lastPage = page.getTotalPages() - 1;
        }
        this.content = page.getContent();
    }
}
