package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.UsuarioComClienteDto;
import com.aster.aster_dashboard_backend.dto.UsuariosProdutoDto;
import com.aster.aster_dashboard_backend.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    public Page<Usuario> findAll(Pageable pageable);

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.UsuariosProdutoDto(p.nome, COUNT(u.id.usuarioChaveUso))
        FROM Usa u
        LEFT JOIN Licenca l ON u.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        GROUP BY p.nome
    """)
    public List<UsuariosProdutoDto> findUsuariosProduto();

    @Query(value = """
        WITH meses_licenca AS (
            SELECT
                u.usuario_chave_uso,
                l.id AS licenca_id,
                l.tipo,
                l.data_registro,
                l.ativa,
                u.cliente_documento,
                l.produto_id,
        
                generate_series(
                    date_trunc('month', l.data_registro),
                    LEAST(
                        CASE
                            WHEN l.tipo = 'Mensal'
                                THEN date_trunc('month', l.data_registro)
        
                            WHEN l.tipo = 'Anual'
                                THEN date_trunc('month', l.data_registro) + interval '11 month'
        
                            WHEN l.tipo = 'Vitalícia'
                                THEN date_trunc('month', CURRENT_DATE)
                                
                            WHEN l.tipo = 'Demo'
                                THEN date_trunc('month', l.data_registro)
        
                            ELSE NULL
                        END,
                        date_trunc('month', CURRENT_DATE)
                    ),
                    interval '1 month'
                ) AS mes
            FROM USA u
            JOIN LICENCA l ON l.id = u.licenca_id
            WHERE l.ativa = TRUE
        )
        
        SELECT
            pr.nome AS produto,
            m.mes::date AS data,
            COUNT(DISTINCT m.usuario_chave_uso) AS usuarios
        FROM meses_licenca m
        JOIN PRODUTO pr ON pr.id = m.produto_id
        WHERE pr.status = 'Comercializável'
        GROUP BY pr.nome, m.mes
        ORDER BY pr.nome, m.mes
    """, nativeQuery = true)
    public List<Object[]> findUsuariosMensaisProduto();

    @Query(value= """
        SELECT
            ROW_NUMBER() OVER (ORDER BY pr.nome) - 1 AS id,
            COUNT(DISTINCT u.usuario_chave_uso) AS value,
            pr.nome AS label
        FROM PRODUTO pr
        JOIN LICENCA l ON l.produto_id = pr.id
        JOIN USA u ON u.licenca_id = l.id
        WHERE pr.status = 'Comercializável' AND l.ativa = TRUE
        GROUP BY pr.nome
        ORDER BY pr.nome
    """, nativeQuery=true)
    public List<Object[]> findUsuariosTotalProduto();

    @Query(value= """
        SELECT new com.aster.aster_dashboard_backend.dto.UsuarioComClienteDto(c.nome, u.chaveUso)
        FROM Usuario u
        JOIN Usa usa ON usa.usuario.chaveUso = u.chaveUso
        JOIN Cliente c ON usa.cliente.documento = c.documento
    """, countQuery= """
        SELECT COUNT(u.chaveUso)
        FROM Usuario u
        JOIN Usa usa ON usa.usuario.chaveUso = u.chaveUso
        JOIN Cliente c ON usa.cliente.documento = c.documento
    """)
    public Page<UsuarioComClienteDto> findUsuarioComClientePaginated(Pageable pageable);

    @Query(value = """
        WITH usuarios_tipo AS (
            SELECT
                u.usuario_chave_uso,
                CASE
                    WHEN i.cliente_documento IS NOT NULL THEN 'Individual'
                    ELSE 'Organizacional'
                END AS tipo_cliente
            FROM USA u
            JOIN LICENCA l ON l.id = u.licenca_id
            LEFT JOIN INDIVIDUAL i ON i.cliente_documento = u.cliente_documento
            LEFT JOIN ORGANIZACAO o ON o.cliente_documento = u.cliente_documento
            WHERE l.ativa = TRUE
        )
        
        SELECT
            0 AS id,
            COUNT(DISTINCT usuario_chave_uso) AS value,
            'Usuários individuais' AS label
        FROM usuarios_tipo
        WHERE tipo_cliente = 'Individual'
        
        UNION ALL
        
        SELECT
            1 AS id,
            COUNT(DISTINCT usuario_chave_uso) AS value,
            'Usuários organizacionais' AS label
        FROM usuarios_tipo
        WHERE tipo_cliente = 'Organizacional'
    """, nativeQuery = true)
    public List<Object[]> findUsuariosTipoCliente();
}
