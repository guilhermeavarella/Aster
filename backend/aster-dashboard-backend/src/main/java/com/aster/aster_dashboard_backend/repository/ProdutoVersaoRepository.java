package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.ProdutoVersao;
import com.aster.aster_dashboard_backend.entity.ids.ProdutoVersaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVersaoRepository extends JpaRepository<ProdutoVersao, ProdutoVersaoId> {
}
