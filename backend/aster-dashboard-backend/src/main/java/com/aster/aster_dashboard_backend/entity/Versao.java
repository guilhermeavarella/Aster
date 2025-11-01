package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="versao")
public class Versao{

    @Id
    @Column(name="numeroDaVersao")
    private String numeroDaVersao;

    @Column(name="arquivoInstalador")
    private String arquivoInstalador;

    @Column(name="dataDeLancamento")
    private String dataDeLancamento;

    @Column(name="patchNotes")
    private String patchNotes;

    @Override
    public String toString() {
        return "Versao{" +
                "número da versão='" + numeroDaVersao + '\'' +
                ", data de lançamento='" + dataDeLancamento + '\'' +
                ", arquivo instalador='" + arquivoInstalador + '\'' +
                ", patch notes='" + patchNotes + '\'' +
                '}';
    }
}
