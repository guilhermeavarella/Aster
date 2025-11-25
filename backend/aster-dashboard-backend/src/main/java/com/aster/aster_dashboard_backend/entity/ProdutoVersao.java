package com.aster.aster_dashboard_backend.entity;

import com.aster.aster_dashboard_backend.entity.id.ProdutoVersaoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="produto_versao")
public class ProdutoVersao{

    @EmbeddedId
    private ProdutoVersaoId id;

    @Column(name="arquivo_instalador")
    private String arquivoInstalador;

    @Column(name="data_lancamento")
    private LocalDate dataLancamento;

    @Column(name="patch_notes")
    private String patchNotes;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name="produto_id")
    private Produto produto;

    @Override
    public String toString() {
        return "ProdutoVersao{" +
                "id=" + id +
                ", arquivoInstalador='" + arquivoInstalador + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", patchNotes='" + patchNotes + '\'' +
                ", produtoId=" + id.getProdutoId() +
                '}';
    }
}
