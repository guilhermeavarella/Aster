package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.ClientesPaisDto;
import com.aster.aster_dashboard_backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    long countByContinente(String continente);

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ClientesPaisDto(c.regiao, COUNT(*))
        FROM Cliente c
        GROUP BY c.regiao
    """)
    public List<ClientesPaisDto> countByRegiao();

    @Query(value="""
        WITH valores_cliente AS (
            SELECT
                a.cliente_documento,
                CASE
                    WHEN i.cliente_documento IS NOT NULL THEN 'Individual'
                    ELSE 'Organizacional'
                END AS tipo_cliente,
                CASE
                    WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual
                    ELSE p.preco_organizacional
                END AS valor_compra
            FROM ADQUIRE a
            JOIN LICENCA l ON l.id = a.licenca_id
            JOIN PACOTE p ON p.nome = a.pacote_nome
            LEFT JOIN INDIVIDUAL i ON i.cliente_documento = a.cliente_documento
            LEFT JOIN ORGANIZACAO o ON o.cliente_documento = a.cliente_documento
            WHERE l.ativa = TRUE
        )
        
        SELECT
            0 AS id,
            ROUND(AVG(CASE WHEN tipo_cliente = 'Individual' THEN valor_compra END)::numeric, 2) AS value,
            'Ticket Médio: Individual' AS label
        FROM valores_cliente
        UNION ALL
        SELECT
            1 AS id,
            ROUND(AVG(CASE WHEN tipo_cliente = 'Organizacional' THEN valor_compra END)::numeric, 2) AS value,
            'Ticket Médio: Organizacional' AS label
        FROM valores_cliente
    """, nativeQuery=true)
    public List<Object[]> findTicketMedio();
}
