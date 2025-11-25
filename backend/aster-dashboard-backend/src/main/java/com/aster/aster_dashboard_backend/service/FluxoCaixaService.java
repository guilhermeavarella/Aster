package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.ReceitaTotalDto;
import com.aster.aster_dashboard_backend.dto.TicketMedioClienteDto;
import com.aster.aster_dashboard_backend.repository.AdquireRepository;
import com.aster.aster_dashboard_backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class FluxoCaixaService {

    private AdquireRepository adquireRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public FluxoCaixaService(AdquireRepository adquireRepository, ClienteRepository clienteRepository) {
        this.adquireRepository = adquireRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<ReceitaTotalDto> findReceitaTotal() {
        List<Object[]> lista = adquireRepository.findReceitaTotal();
        return lista.stream().map(o -> new ReceitaTotalDto(
                ((Date) o[0]),
                ((BigDecimal) o[1])
        )).toList();
    }

    public List<TicketMedioClienteDto> findTicketMedio() {
        List<Object[]> lista = clienteRepository.findTicketMedio();
        return lista.stream().map(o -> new TicketMedioClienteDto(
                ((Integer) o[0]),
                ((BigDecimal) o[1]),
                (o[2].toString())
        )).toList();
    }
}
