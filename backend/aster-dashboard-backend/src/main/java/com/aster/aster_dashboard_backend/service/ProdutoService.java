package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ProdutoConverter;
import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.entity.Categoria;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.id.CategoriaId;
import com.aster.aster_dashboard_backend.repository.CategoriaRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository repository;
    private CategoriaRepository categoriaRepository;
    private ProdutoConverter converter;

    @Autowired
    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository, ProdutoConverter converter) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
        this.converter = converter;
    }

    public List<ProdutoInicioDto> findProdutosInicio() {
        return repository.findProdutosInicio();
    }

    public Page<ProdutoDto> findAllPaginated(int page) {
        Page<Produto> produtos = repository.findAll(PageRequest.of(page, 15));

        return produtos.map(converter::toDto);
    }

    public ProdutoDto findById(String id) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        return converter.toDto(produto.get());

    }

    public List<TotalVendasProdutoDto> findTotalVendasProduto() {
        return repository.findTotalVendasProduto();
    }

    public MensalProdutoDto<DataVendasDto> findVendasMensaisProduto() {
        List<VendasMensaisProdutoDto> dtos = repository.findVendasMensaisProduto();

        MensalProdutoDto<DataVendasDto> resultado = new MensalProdutoDto<>();

        for (VendasMensaisProdutoDto dto : dtos) {
            DataVendasDto dataVendas = new DataVendasDto(dto.getData(), dto.getVendas());

            switch (dto.getProduto()) {
                case "Nova"       -> resultado.getNova().add(dataVendas);
                case "Celeste"    -> resultado.getCeleste().add(dataVendas);
                case "Solaris"    -> resultado.getSolaris().add(dataVendas);
                case "PrismaCut"  -> resultado.getPrismaCut().add(dataVendas);
                case "EtherFX"    -> resultado.getEtherFX().add(dataVendas);
                case "Framea"     -> resultado.getFramea().add(dataVendas);
                case "Aikonic"    -> resultado.getAikonic().add(dataVendas);
                case "Orbit"      -> resultado.getOrbit().add(dataVendas);
                case "Graphia"    -> resultado.getGraphia().add(dataVendas);
                case "Nebula3D"   -> resultado.getNebula3D().add(dataVendas);
                case "Spectra"    -> resultado.getSpectra().add(dataVendas);
                case "ScreenFlow" -> resultado.getScreenFlow().add(dataVendas);
                case "LumenFrame" -> resultado.getLumenFrame().add(dataVendas);
                case "AuraPaint"  -> resultado.getAuraPaint().add(dataVendas);
                case "LumenDraw"  -> resultado.getLumenDraw().add(dataVendas);
                case "BloomBank"  -> resultado.getBloomBank().add(dataVendas);
            }
        }

        return resultado;
    }

    public List<ReceitaTotalProdutoDto> findReceitaTotalProduto() {
        List<Object[]> lista = repository.findReceitaTotalProduto();
        return lista.stream().map(o -> new ReceitaTotalProdutoDto(
                (o[0].toString()),
                ((BigDecimal) o[1])
        )).toList();
    }

    public MensalProdutoDto<DataReceitaDto> findReceitaMensalProduto() {
        List<Object[]> lista = repository.findReceitaMensalProduto();
        List<ReceitaMensalProdutoDto> dtos = lista.stream().map(o-> new ReceitaMensalProdutoDto(
                (o[0].toString()),
                ((Date) o[1]),
                ((BigDecimal) o[2])
        )).toList();

        MensalProdutoDto<DataReceitaDto> resultado = new MensalProdutoDto<>();

        for (ReceitaMensalProdutoDto dto : dtos) {
            DataReceitaDto dataReceita = new DataReceitaDto(dto.getData(), dto.getReceita());

            switch (dto.getProduto()) {
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
            }
        }

        return resultado;
    }

    public List<ProdutoDto> findProdutosMaisPopulares() {
        List<Produto> produtos = repository.findProdutosMaisPopulares();
        return produtos.stream().map(converter::toDto).toList();
    }

    public List<ProdutoDto> findProdutosComercializaveis() {
        List<Produto> lista = repository.findByStatus("Comercializável");
        return lista.stream().map(converter::toDto).toList();
    }

    @Transactional
    public void create(ProdutoDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Um ID deve ser fornecido!");
        }

        if (repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Esse ID já existe!");
        }

        Produto produto = converter.toEntity(dto);
        repository.save(produto);

        List<String> categorias = dto.getCategorias();

        for (String categoria : categorias) {
            CategoriaId categoriaId = new CategoriaId(categoria, dto.getId());
            Categoria entity = new Categoria(categoriaId, produto);

            categoriaRepository.save(entity);
        }
    }

    @Transactional
    public void update(String id, ProdutoDto dto) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Esse ID não existe!");
        }

        Produto entity = produto.get();

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        if (dto.getIcone() != null) {
            entity.setIcone(dto.getIcone());
        }

        if (dto.getDescricaoBreve() != null) {
            entity.setDescricaoBreve(dto.getDescricaoBreve());
        }

        if (dto.getDescricao() != null) {
            entity.setDescricaoCompleta(dto.getDescricao());
        }

        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }

        repository.save(entity);

        if (dto.getCategorias() != null) {

            for (String categoria : categoriaRepository.findNomesByProdutoId(entity.getId())) {
                CategoriaId categoriaId = new CategoriaId(categoria, entity.getId());
                Categoria categoriaEntity = categoriaRepository.findById(categoriaId).orElse(null);

                if (categoriaEntity != null) {
                    categoriaRepository.delete(categoriaEntity);
                }
            }
            List<String> novasCategorias = dto.getCategorias();

            for (String categoria : novasCategorias) {
                CategoriaId categoriaId = new CategoriaId(categoria, dto.getId());
                Categoria novaCategoria = new Categoria(categoriaId, entity);

                categoriaRepository.save(novaCategoria);
            }
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Esse ID não existe!");
        }

        Produto entity = produto.get();
        ProdutoDto dto = converter.toDto(entity);

        for (String categoria : dto.getCategorias()) {
            CategoriaId categoriaId = new CategoriaId(categoria, dto.getId());
            Categoria categoriaEntity = categoriaRepository.findById(categoriaId).orElse(null);

            if (categoriaEntity != null) {
                categoriaRepository.delete(categoriaEntity);
            }
        }

        repository.delete(entity);
    }
}
