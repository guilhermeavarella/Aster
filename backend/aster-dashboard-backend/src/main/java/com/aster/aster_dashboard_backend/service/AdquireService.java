package com.aster.aster_dashboard_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aster.aster_dashboard_backend.converter.AdquireConverter;
import com.aster.aster_dashboard_backend.dto.AdquireDto;
import com.aster.aster_dashboard_backend.entity.Adquire;
import com.aster.aster_dashboard_backend.entity.Cliente;
import com.aster.aster_dashboard_backend.entity.Licenca;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.entity.id.AdquireId;
import com.aster.aster_dashboard_backend.repository.AdquireRepository;
import com.aster.aster_dashboard_backend.repository.ClienteRepository;
import com.aster.aster_dashboard_backend.repository.LicencaRepository;
import com.aster.aster_dashboard_backend.repository.PacoteRepository;

@Service
public class AdquireService {

    private PacoteRepository pacoteRepository;
    private LicencaRepository licencaRepository;
    private ClienteRepository clienteRepository;
    private AdquireRepository repository;
    private AdquireConverter converter;
    
    @Autowired
    public AdquireService(PacoteRepository pacoteRepository, LicencaRepository licencaRepository, ClienteRepository clienteRepository, AdquireRepository repository, AdquireConverter converter) {
        this.pacoteRepository = pacoteRepository;
        this.licencaRepository = licencaRepository;
        this.clienteRepository = clienteRepository;
        this.repository = repository;
        this.converter = converter;
    }

    public List<AdquireDto> findAll() {
        List<Adquire> adquire = repository.findAll();
        return adquire.stream().map(converter::toDto).toList();
    }

    @Transactional
    public void create(AdquireDto dto) {

        if (dto.getClienteDocumento() == null) {
            throw new IllegalArgumentException("Um documento deve ser fornecido!");
        }

        if (dto.getLicencaId() == null) {
            throw new IllegalArgumentException("Um id deve ser fornecido!");
        }

        if (dto.getPacoteNome() == null) {
            throw new IllegalArgumentException("Um nome deve ser fornecido!");
        }

        Optional<Cliente> cliente = clienteRepository.findById(dto.getClienteDocumento());
        if (cliente.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento de cliente!");
        }

        Optional<Licenca> licenca = licencaRepository.findById(dto.getLicencaId());
        if (licenca.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID de licença!");
        }

        Optional<Pacote> pacote = pacoteRepository.findById(dto.getPacoteNome());
        if (pacote.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse nome de pacote!");
        }

        AdquireId id = new AdquireId();
        id.setClienteDocumento(dto.getClienteDocumento());
        id.setLicencaId(dto.getLicencaId());
        id.setPacoteNome(dto.getPacoteNome());

        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Essa combinação já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

     @Transactional
    public void delete(String clienteDocumento, String licencaId, String pacoteNome) {

        if (clienteDocumento == null) {
            throw new IllegalArgumentException("Um documento de cliente deve ser fornecido!");
        }
        if (licencaId == null) {
            throw new IllegalArgumentException("Um ID de licença deve ser fornecido!");
        }
        if (pacoteNome == null) {
            throw new IllegalArgumentException("Um nome de pacote deve ser fornecida!");
        }

        Optional<Cliente> cliente = clienteRepository.findById(clienteDocumento);
        if (cliente.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento de cliente!");
        }
        Optional<Licenca> licenca = licencaRepository.findById(licencaId);
        if (licenca.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID de licença!");
        }
         Optional<Pacote> pacote = pacoteRepository.findById(pacoteNome);
        if (pacote.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse nome de pacote!");
        }

        AdquireId id = new AdquireId();
        id.setClienteDocumento(clienteDocumento);
        id.setLicencaId(licencaId);
        id.setPacoteNome(pacoteNome);

        Optional<Adquire> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento de cliente, ID de licença e chave de uso de usuário!");
        }

        repository.delete(result.get());
    }

}