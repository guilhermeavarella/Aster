package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.PacoteConverter;
import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacoteService {

    private PacoteRepository repository;
    private PacoteConverter converter;

    @Autowired
    public PacoteService(PacoteRepository repository, PacoteConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<PacoteDto> findAll() {
        List<Pacote> lista = repository.findAll();
        return lista.stream().map(converter::toDto).toList();
    }

    public Page<PacoteDto> findAllPaginated(int page) {
        Page<Pacote> pacotes = repository.findAll(PageRequest.of(page, 15));
        return pacotes.map(converter::toDto);
    }

    public PacoteDto findByNome(String nome) {
        Optional<Pacote> result = repository.findById(nome);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse nome!");
        }

        return converter.toDto(result.get());
    }

    public List<TotalVendasPacoteDto> findTotalVendasPacote() {
        return repository.findTotalVendasPacote();
    }

    public List<VendasMensaisPacoteDto> findVendasMensaisPacote() {
        return repository.findVendasMensaisPacote();
    }

    public List<ReceitaTotalPacoteDto> findReceitaTotalPacote() {
        List<Object[]> lista = repository.findReceitaTotalPacote();
        return lista.stream().map(o -> new ReceitaTotalPacoteDto(
                (o[0].toString()),
                ((BigDecimal) o[1])
        )).toList();
    }

    public MensalPacoteDto<DataReceitaDto>  findReceitaMensalPacote() {
        List<Object[]> lista = repository.findReceitaMensalPacote();
        List<ReceitaMensalPacoteDto> dtos = lista.stream().map(o-> new ReceitaMensalPacoteDto(
                (o[0].toString()),
                ((Date) o[1]),
                ((BigDecimal) o[2])
        )).toList();

        MensalPacoteDto<DataReceitaDto> resultado = new MensalPacoteDto<>();

        for (ReceitaMensalPacoteDto dto : dtos) {
            DataReceitaDto dataReceita = new DataReceitaDto(dto.getData(), dto.getReceita());

            switch (dto.getPacote()) {
                case "Nova"       -> resultado.getNova().add(dataReceita);
                case "Celeste"    -> resultado.getCeleste().add(dataReceita);
                case "Solaris"    -> resultado.getSolaris().add(dataReceita);
                case "PrismaCut"  -> resultado.getPrismaCut().add(dataReceita);
                case "EtherFX"    -> resultado.getEtherFX().add(dataReceita);
                case "Framea"     -> resultado.getFramea().add(dataReceita);
                case "Aikonic"    -> resultado.getAikonic().add(dataReceita);
                case "Orbit"      -> resultado.getOrbit().add(dataReceita);
                case "Graphia"    -> resultado.getGraphia().add(dataReceita);
                case "Nebula3D"   -> resultado.getNebula3D().add(dataReceita);
                case "Spectra"    -> resultado.getSpectra().add(dataReceita);
                case "ScreenFlow" -> resultado.getScreenFlow().add(dataReceita);
                case "LumenFrame" -> resultado.getLumenFrame().add(dataReceita);
                case "AuraPaint"  -> resultado.getAuraPaint().add(dataReceita);
                case "LumenDraw"  -> resultado.getLumenDraw().add(dataReceita);
                case "BloomBank"  -> resultado.getBloomBank().add(dataReceita);
                case "Design" ->  resultado.getDesign().add(dataReceita);
                case "Fotos" -> resultado.getFotos().add(dataReceita);
                case "Vídeos" -> resultado.getVideos().add(dataReceita);
                case "Animação"  -> resultado.getAnimacao().add(dataReceita);
                case "Ilustração" -> resultado.getIlustracao().add(dataReceita);
                case "Documentos"  -> resultado.getDocumentos().add(dataReceita);
                case "Social Media"  -> resultado.getSocial_Media().add(dataReceita);
                case "3D" -> resultado.get_3D().add(dataReceita);
            }
        }

        return resultado;
    }

    public List<MediaAvaliacoesPacoteDto> findMediaAvaliacoesPacote() {
        return repository.findMediaAvaliacoesPacote();
    }

    public MensalPacoteDto<DataAvaliacaoDto> findAvaliacaoMensalPacote() {
        List<Object[]> lista = repository.findAvaliacaoMensalPacote();
        List<AvaliacaoMensalPacoteDto> dtos = lista.stream().map(o -> new AvaliacaoMensalPacoteDto(
                (o[0].toString()),
                ((Date) o[1]),
                ((BigDecimal) o[2])
        )).toList();

        MensalPacoteDto<DataAvaliacaoDto> resultado = new MensalPacoteDto<>();

        for (AvaliacaoMensalPacoteDto dto : dtos) {
            DataAvaliacaoDto dataAvaliacao = new  DataAvaliacaoDto(dto.getData(), dto.getAvaliacao());

            switch (dto.getPacote()) {
                case "Nova"       -> resultado.getNova().add(dataAvaliacao);
                case "Celeste"    -> resultado.getCeleste().add(dataAvaliacao);
                case "Solaris"    -> resultado.getSolaris().add(dataAvaliacao);
                case "PrismaCut"  -> resultado.getPrismaCut().add(dataAvaliacao);
                case "EtherFX"    -> resultado.getEtherFX().add(dataAvaliacao);
                case "Framea"     -> resultado.getFramea().add(dataAvaliacao);
                case "Aikonic"    -> resultado.getAikonic().add(dataAvaliacao);
                case "Orbit"      -> resultado.getOrbit().add(dataAvaliacao);
                case "Graphia"    -> resultado.getGraphia().add(dataAvaliacao);
                case "Nebula3D"   -> resultado.getNebula3D().add(dataAvaliacao);
                case "Spectra"    -> resultado.getSpectra().add(dataAvaliacao);
                case "ScreenFlow" -> resultado.getScreenFlow().add(dataAvaliacao);
                case "LumenFrame" -> resultado.getLumenFrame().add(dataAvaliacao);
                case "AuraPaint"  -> resultado.getAuraPaint().add(dataAvaliacao);
                case "LumenDraw"  -> resultado.getLumenDraw().add(dataAvaliacao);
                case "BloomBank"  -> resultado.getBloomBank().add(dataAvaliacao);
                case "Design" ->  resultado.getDesign().add(dataAvaliacao);
                case "Fotos" -> resultado.getFotos().add(dataAvaliacao);
                case "Vídeos" -> resultado.getVideos().add(dataAvaliacao);
                case "Animação"  -> resultado.getAnimacao().add(dataAvaliacao);
                case "Ilustração" -> resultado.getIlustracao().add(dataAvaliacao);
                case "Documentos"  -> resultado.getDocumentos().add(dataAvaliacao);
                case "Social Media"  -> resultado.getSocial_Media().add(dataAvaliacao);
                case "3D" -> resultado.get_3D().add(dataAvaliacao);
            }
        }

        return resultado;

    }

    public List<PacoteDto> findPacotesMaisPopulares() {
        List<Pacote> pacotes = repository.findPacotesMaisPopulares();
        return pacotes.stream().map(converter::toDto).toList();
    }

    @Transactional
    public void create(PacoteDto dto) {

        if (dto.getNome() == null) {
            throw new IllegalArgumentException("Um nome deve ser fornecido!");
        }

        if (repository.existsById(dto.getNome())) {
            throw new IllegalArgumentException("Esse nome já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String nome, PacoteDto dto) {
        Optional<Pacote> result = repository.findById(nome);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse nome!");
        }

        Pacote entity = result.get();

        if (dto.getPrecoIndividual() != null) {
            entity.setPrecoIndividual(dto.getPrecoIndividual());
        }

        if (dto.getPrecoOrganizacional() != null) {
            entity.setPrecoOrganizacional(dto.getPrecoOrganizacional());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String nome) {
        Optional<Pacote> result = repository.findById(nome);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse nome!");
        }

        repository.delete(result.get());
    }
}
