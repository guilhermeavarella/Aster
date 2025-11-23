package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.ProdutoInicioDto;
import com.aster.aster_dashboard_backend.dto.TotalVendasPacoteDto;
import com.aster.aster_dashboard_backend.dto.TotalVendasProdutoDto;
import com.aster.aster_dashboard_backend.dto.VendasMensaisProdutoDto;
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
        SELECT new com.aster.aster_dashboard_backend.dto.TotalVendasPacoteDto(p.nome, COUNT(*))
        FROM Adquire a
        LEFT JOIN Pacote p ON a.id.pacoteNome = p.nome
        GROUP BY p.nome
    """)
    public List<TotalVendasPacoteDto> findTotalVendasPacote();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.VendasMensaisProdutoDto(p.nome, DATE_TRUNC('month', l.dataRegistro), COUNT(*))
        FROM Adquire a
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        GROUP BY DATE_TRUNC('month', l.dataRegistro), p.nome
    """)
    public List<VendasMensaisProdutoDto> findVendasMensaisProduto();
}
