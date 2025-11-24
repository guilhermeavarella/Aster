package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.ReceitaTotalDto;
import com.aster.aster_dashboard_backend.repository.AdquireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class FluxoCaixaService {

    private AdquireRepository adquireRepository;

    @Autowired
    public FluxoCaixaService(AdquireRepository adquireRepository) {
        this.adquireRepository = adquireRepository;
    }

    public List<ReceitaTotalDto> findReceitaTotal() {
        List<Object[]> lista = adquireRepository.findReceitaTotal();
        return lista.stream().map(o -> new ReceitaTotalDto(
                ((Date) o[0]),
                ((BigDecimal) o[1])
        )).toList();
    }
}
