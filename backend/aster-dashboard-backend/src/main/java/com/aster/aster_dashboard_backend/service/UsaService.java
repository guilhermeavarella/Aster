package com.aster.aster_dashboard_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aster.aster_dashboard_backend.converter.UsaConverter;
import com.aster.aster_dashboard_backend.dto.UsaDto;
import com.aster.aster_dashboard_backend.entity.Cliente;
import com.aster.aster_dashboard_backend.entity.Licenca;
import com.aster.aster_dashboard_backend.entity.Usa;
import com.aster.aster_dashboard_backend.entity.Usuario;
import com.aster.aster_dashboard_backend.entity.id.UsaId;
import com.aster.aster_dashboard_backend.repository.ClienteRepository;
import com.aster.aster_dashboard_backend.repository.LicencaRepository;
import com.aster.aster_dashboard_backend.repository.UsaRepository;
import com.aster.aster_dashboard_backend.repository.UsuarioRepository;

@Service
public class UsaService {

    private ClienteRepository clienteRepository;
    private LicencaRepository licencaRepository;
    private UsuarioRepository usuarioRepository;
    private UsaRepository repository;
    private UsaConverter converter;
    
    @Autowired
    public UsaService(UsaRepository repository, ClienteRepository clienteRepository,
    LicencaRepository licencaRepository,
    UsuarioRepository usuarioRepository, UsaConverter converter) {
        this.clienteRepository = clienteRepository;
        this.licencaRepository = licencaRepository;
        this.usuarioRepository = usuarioRepository;
        this.repository = repository;
        this.converter = converter;
    }

    public List<UsaDto> findAll() {
        List<Usa> usa = repository.findAll();
        return usa.stream().map(converter::toDto).toList();
    }
    

     @Transactional
    public void create(UsaDto dto) {

        if (dto.getClienteDocumento() == null) {
            throw new IllegalArgumentException("Um documento deve ser fornecido!");
        }

        if (dto.getLicencaId() == null) {
            throw new IllegalArgumentException("Um id deve ser fornecido!");
        }

        if (dto.getUsuarioChaveUso() == null) {
            throw new IllegalArgumentException("Uma chave de uso deve ser fornecida!");
        }

        Optional<Cliente> cliente = clienteRepository.findById(dto.getClienteDocumento());
        if (cliente.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento de cliente!");
        }

        Optional<Licenca> licenca = licencaRepository.findById(dto.getLicencaId());
        if (licenca.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID de licença!");
        }

        Optional<Usuario> usuario = usuarioRepository.findById(dto.getUsuarioChaveUso());
        if (usuario.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com essa chave de uso de usuário!");
        }

        UsaId id = new UsaId();
        id.setClienteDocumento(dto.getClienteDocumento());
        id.setLicencaId(dto.getLicencaId());
        id.setUsuarioChaveUso(dto.getUsuarioChaveUso());

        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Essa combinação já existe!");
        }

        repository.save(converter.toEntity(dto));
}

    @Transactional
    public void delete(String clienteDocumento, String licencaId, String usuarioChaveUso) {

        if (clienteDocumento == null) {
            throw new IllegalArgumentException("Um documento de cliente deve ser fornecido!");
        }
        if (licencaId == null) {
            throw new IllegalArgumentException("Um ID de licença deve ser fornecido!");
        }
        if (usuarioChaveUso == null) {
            throw new IllegalArgumentException("Uma chave de uso de usuário deve ser fornecida!");
        }

        Optional<Cliente> cliente = clienteRepository.findById(clienteDocumento);
        if (cliente.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento de cliente!");
        }
        Optional<Licenca> licenca = licencaRepository.findById(licencaId);
        if (licenca.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID de licença!");
        }
         Optional<Usuario> usuario = usuarioRepository.findById(usuarioChaveUso);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com essa chave de uso de usuário!");
        }

        UsaId id = new UsaId();
        id.setClienteDocumento(clienteDocumento);
        id.setLicencaId(licencaId);
        id.setUsuarioChaveUso(usuarioChaveUso);

        Optional<Usa> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento de cliente, ID de licença e chave de uso de usuário!");
        }

        repository.delete(result.get());
    }
}
