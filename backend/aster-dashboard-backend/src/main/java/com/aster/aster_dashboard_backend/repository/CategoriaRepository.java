package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Categoria;
import com.aster.aster_dashboard_backend.entity.id.CategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, CategoriaId> {

    @Query("SELECT c.id.categoria FROM Categoria c WHERE c.id.produtoId = :produtoId")
    List<String> findNomesByProdutoId(@Param("produtoId") String produtoId);
}
