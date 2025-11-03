package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="produto_versao")
public class ProdutoVersao{

    @EmbeddedId
    private ProdutoVersaoId id;

    @Column(name="arquivo_instalador", length = 50)
    private String arquivoInstalador;

    @Column(name="data_lancamento")
    private LocalDate dataLancamento;

    @Column(name="patch_notes", length = 200)
    private String patchNotes;

    @MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Override
    public String toString() {
        return "ProdutoVersao{" +
                "numeroVersao='" + id.getNumeroVersao + '\'' +
                ", produtoId='" + id.getProdutoId + '\'' +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", arquivoInstalador='" + arquivoInstalador + '\'' +
                ", patchNotes='" + patchNotes + '\'' +
                ", produtoNome='" + (produto != null ? produto.getNome() : null) + '\'' +
                '}';
    }
}
