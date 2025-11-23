package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ProdutoInicioDto(p.icone, CAST(AVG(f.avaliacao) AS bigdecimal), (
                SELECT pv2.id.numeroVersao
                FROM ProdutoVersao pv2
                WHERE pv2.dataLancamento = (
                        SELECT MAX(pv1.dataLancamento)
                        FROM ProdutoVersao pv1
                )
                )
        )
        FROM Produto p
        LEFT JOIN ProdutoVersao pv3 ON pv3.produto = p
        LEFT JOIN Feedback f ON f.produto = p
        GROUP BY p.id
        """)
    public List<ProdutoInicioDto> findProdutosInicio();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.TotalVendasProdutoDto(p.nome, COUNT(*))
        FROM Adquire a
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        GROUP BY p.nome
    """)
    public List<TotalVendasProdutoDto> findTotalVendasProduto();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.VendasMensaisProdutoDto(p.nome, DATE_TRUNC('month', l.dataRegistro), COUNT(*))
        FROM Adquire a
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        GROUP BY DATE_TRUNC('month', l.dataRegistro), p.nome
    """)
    public List<VendasMensaisProdutoDto> findVendasMensaisProduto();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ReceitaTotalProdutoDto(p.nome, CAST((SUM(pct.precoIndividual) + SUM(pct.precoOrganizacional)) AS bigdecimal))
        FROM Adquire a
        LEFT JOIN Pacote pct ON a.id.pacoteNome = pct.nome
        LEFT JOIN Contem c ON c.id.pacoteNome = pct.nome
        LEFT JOIN Produto p ON p.id = c.id.produtoId
        WHERE pct.nome IN ('Nova', 'Celeste', 'Solaris', 'Eclipt', 'PrismaCut', 'EtherFX', 'Framea', 'Aikonic', 'Orbit', 'Graphia', 'Nebula3D', 'Spectra', 'ScreenFlow', 'LumenFrame', 'AuraPaint', 'LumenDraw', 'Picta', 'BloomBank', 'ChromaSwap')
        GROUP BY p.nome
    """)
    public List<ReceitaTotalProdutoDto> findReceitaTotalProduto();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ReceitaMensalProdutoDto(p.nome, DATE_TRUNC('month', l.dataRegistro), CAST((SUM(pct.precoIndividual) + SUM(pct.precoOrganizacional)) AS bigdecimal))
        FROM Adquire a
        LEFT JOIN Pacote pct ON a.id.pacoteNome = pct.nome
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Contem c ON c.id.pacoteNome = pct.nome
        LEFT JOIN Produto p ON p.id = c.id.produtoId
        WHERE pct.nome IN ('Nova', 'Celeste', 'Solaris', 'Eclipt', 'PrismaCut', 'EtherFX', 'Framea', 'Aikonic', 'Orbit', 'Graphia', 'Nebula3D', 'Spectra', 'ScreenFlow', 'LumenFrame', 'AuraPaint', 'LumenDraw', 'Picta', 'BloomBank', 'ChromaSwap')
        GROUP BY DATE_TRUNC('month', l.dataRegistro), p.nome
    """)
    public List<ReceitaMensalProdutoDto> findReceitaMensalProduto();
}
