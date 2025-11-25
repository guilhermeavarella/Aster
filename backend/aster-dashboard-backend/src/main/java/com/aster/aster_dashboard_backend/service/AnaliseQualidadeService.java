package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.PorcentagemDto;
import com.aster.aster_dashboard_backend.dto.QuantidadeDto;
import com.aster.aster_dashboard_backend.dto.UsuarioComClienteDto;
import com.aster.aster_dashboard_backend.dto.VersaoDto;
import com.aster.aster_dashboard_backend.repository.LicencaRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoVersaoRepository;
import com.aster.aster_dashboard_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AnaliseQualidadeService {

    private LicencaRepository licencaRepository;
    private UsuarioRepository usuarioRepository;
    private ProdutoVersaoRepository produtoVersaoRepository;

    @Autowired
    public AnaliseQualidadeService(LicencaRepository licencaRepository, UsuarioRepository usuarioRepository, ProdutoVersaoRepository produtoVersaoRepository) {
        this.licencaRepository = licencaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoVersaoRepository = produtoVersaoRepository;
    }

    public List<PorcentagemDto> findPorcentagemAtiva() {
        List<Object[]> lista = licencaRepository.findPorcentagemAtiva();
        return lista.stream().map(o -> new PorcentagemDto(
                ((Integer) o[0]),
                ((BigDecimal) o[1]),
                (o[2].toString())
        )).toList();
    }

    public List<QuantidadeDto> findUsuariosTotalProduto() {
        List<Object[]> lista = usuarioRepository.findUsuariosTotalProduto();
        return lista.stream().map(o-> new QuantidadeDto(
                (((Long) o[0]).intValue()),
                ((Long) o[1]),
                (o[2].toString())
        )).toList();
    }

    public List<VersaoDto> findVersoesMaisRecentes() {
        return produtoVersaoRepository.findVersoesMaisRecentes();
    }

    public Page<UsuarioComClienteDto> findUsuarioComClientePaginated(int page) {
        return usuarioRepository.findUsuarioComClientePaginated(PageRequest.of(page, 15));
    }

    public List<QuantidadeDto> findUsuariosTipoCliente() {
        List<Object[]> lista = usuarioRepository.findUsuariosTipoCliente();
        return lista.stream().map(o -> new QuantidadeDto(
                ((Integer)o[0]),
                ((Long) o[1]),
                (o[2].toString())
        )).toList();
    }
}
