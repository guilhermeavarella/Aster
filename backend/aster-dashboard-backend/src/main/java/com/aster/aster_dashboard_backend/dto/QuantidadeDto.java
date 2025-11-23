package com.aster.aster_dashboard_backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuantidadeDto {

    private int id;
    private Long value;
    private String label;
}
