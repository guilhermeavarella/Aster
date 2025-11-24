package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.VersaoDto;
import com.aster.aster_dashboard_backend.entity.ProdutoVersao;
import com.aster.aster_dashboard_backend.entity.id.ProdutoVersaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoVersaoRepository extends JpaRepository<ProdutoVersao, ProdutoVersaoId> {

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.VersaoDto(v.id.numeroVersao, v.produto.id, p.nome, v.dataLancamento, v.arquivoInstalador, v.patchNotes)
        FROM ProdutoVersao v
        JOIN Produto p ON v.produto.id = p.id
        ORDER BY v.dataLancamento DESC
        LIMIT 4 OFFSET 0
    """)
    List<VersaoDto> findVersoesMaisRecentes();
}
