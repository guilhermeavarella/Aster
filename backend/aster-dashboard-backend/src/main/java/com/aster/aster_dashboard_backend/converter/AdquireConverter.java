package com.aster.aster_dashboard_backend.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aster.aster_dashboard_backend.dto.AdquireDto;
import com.aster.aster_dashboard_backend.entity.Adquire;
import com.aster.aster_dashboard_backend.entity.Cliente;
import com.aster.aster_dashboard_backend.entity.Licenca;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.entity.id.AdquireId;
import com.aster.aster_dashboard_backend.repository.ClienteRepository;
import com.aster.aster_dashboard_backend.repository.LicencaRepository;
import com.aster.aster_dashboard_backend.repository.PacoteRepository;

@Component
public class AdquireConverter {

    private PacoteRepository pacoteRepository;
    private LicencaRepository licencaRepository;
    private ClienteRepository clienteRepository;
    
    @Autowired
    public AdquireConverter(PacoteRepository pacoteRepository, LicencaRepository licencaRepository, ClienteRepository clienteRepository) {
        this.pacoteRepository = pacoteRepository;
        this.licencaRepository = licencaRepository;
        this.clienteRepository = clienteRepository;
    }

    public AdquireDto toDto(Adquire entity) {
        AdquireDto dto = new AdquireDto();

        dto.setPacoteNome(entity.getId().getPacoteNome());
        dto.setClienteDocumento(entity.getId().getClienteDocumento());
        dto.setLicencaId(entity.getId().getLicencaId());

        return dto;
    }

    public Adquire toEntity(AdquireDto dto) {
        Adquire entity = new Adquire();

        Optional<Pacote> pacote = pacoteRepository.findById((dto.getPacoteNome()));
        if (pacote.isEmpty()) {
            throw new IllegalArgumentException("Não existe um pacote com esse nome!");
        }
        entity.setPacote(pacote.get());

        Optional<Cliente> cliente = clienteRepository.findById((dto.getClienteDocumento()));
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Não existe um cliente com esse documento!");
        }
        entity.setCliente(cliente.get());

        Optional<Licenca> licenca = licencaRepository.findById((dto.getLicencaId()));
        if (licenca.isEmpty()) {
            throw new IllegalArgumentException("Não existe uma licença com esse ID!");
        }
        entity.setLicenca(licenca.get());

        AdquireId id = new AdquireId(dto.getPacoteNome(), dto.getClienteDocumento(), dto.getLicencaId());
        entity.setId(id);

        return entity;
    }
}