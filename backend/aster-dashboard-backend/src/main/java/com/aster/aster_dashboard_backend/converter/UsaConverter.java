package com.aster.aster_dashboard_backend.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aster.aster_dashboard_backend.dto.ContemDto;
import com.aster.aster_dashboard_backend.dto.UsaDto;
import com.aster.aster_dashboard_backend.entity.Cliente;
import com.aster.aster_dashboard_backend.entity.Contem;
import com.aster.aster_dashboard_backend.entity.Licenca;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.Usa;
import com.aster.aster_dashboard_backend.entity.Usuario;
import com.aster.aster_dashboard_backend.entity.id.ContemId;
import com.aster.aster_dashboard_backend.entity.id.UsaId;
import com.aster.aster_dashboard_backend.repository.ClienteRepository;
import com.aster.aster_dashboard_backend.repository.LicencaRepository;
import com.aster.aster_dashboard_backend.repository.UsuarioRepository;

@Component
public class UsaConverter {

    private UsuarioRepository usuarioRepository;
    private LicencaRepository licencaRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public UsaConverter(ClienteRepository clienteRepository, LicencaRepository licencaRepository,
    UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.licencaRepository = licencaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public UsaDto toDto(Usa entity) {
        UsaDto dto = new UsaDto();

        dto.setClienteDocumento(entity.getId().getClienteDocumento());
        dto.setLicencaId(entity.getId().getLicencaId());
        dto.setUsuarioChaveUso(entity.getId().getUsuarioChaveUso());

        return dto;
    }

    public Usa toEntity(UsaDto dto) {
        Usa entity = new Usa();

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

        Optional<Usuario> usuario = usuarioRepository.findById((dto.getUsuarioChaveUso()));
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Não existe um usuário com essa chave de uso!");
        }
        entity.setUsuario(usuario.get());

        UsaId id = new UsaId(dto.getUsuarioChaveUso(), dto.getLicencaId(), dto.getClienteDocumento());
        entity.setId(id);

        return entity;
    } 
}
