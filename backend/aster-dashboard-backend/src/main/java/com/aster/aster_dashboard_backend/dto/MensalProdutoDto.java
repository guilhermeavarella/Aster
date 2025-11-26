package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensalProdutoDto<T> {

    List<T> Nova = new ArrayList<>();
    List<T> Celeste = new ArrayList<>();
    List<T> Solaris = new ArrayList<>();
    List<T> PrismaCut = new ArrayList<>();
    List<T> EtherFX = new ArrayList<>();
    List<T> Framea = new ArrayList<>();
    List<T> Aikonic = new ArrayList<>();
    List<T> Orbit = new ArrayList<>();
    List<T> Graphia = new ArrayList<>();
    List<T>Nebula3D = new ArrayList<>();
    List<T> Spectra = new ArrayList<>();
    List<T> ScreenFlow = new ArrayList<>();
    List<T> LumenFrame = new ArrayList<>();
    List<T> AuraPaint = new ArrayList<>();
    List<T> LumenDraw = new ArrayList<>();
    List<T> BloomBank = new ArrayList<>();
}
