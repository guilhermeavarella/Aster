package com.aster.aster_dashboard_backend.controller.api.painel;

import com.aster.aster_dashboard_backend.dto.AvaliacaoMensalPacoteDto;
import com.aster.aster_dashboard_backend.dto.MediaAvaliacoesPacoteDto;
import com.aster.aster_dashboard_backend.dto.UsuariosMensaisProdutoDto;
import com.aster.aster_dashboard_backend.dto.UsuariosProdutoDto;
import com.aster.aster_dashboard_backend.service.PacoteService;
import com.aster.aster_dashboard_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painel/d/tech")
public class TechDController {

    private UsuarioService usuarioService;
    private PacoteService pacoteService;

    @Autowired
    public TechDController(UsuarioService usuarioService, PacoteService pacoteService) {
        this.usuarioService = usuarioService;
        this.pacoteService = pacoteService;
    }

    @GetMapping("/usuarios-produto")
    public List<UsuariosProdutoDto> findUsuariosProduto() {
        return usuarioService.findUsuariosProduto();
    }

    @GetMapping("/media-avaliacoes-pacote")
    public List<MediaAvaliacoesPacoteDto> findMediaAvaliacoesPacote() {
        return pacoteService.findMediaAvaliacoesPacote();
    }

    @GetMapping("/usuarios-mensais-produto")
    public List<UsuariosMensaisProdutoDto> findUsuariosMensaisProduto() {
        return usuarioService.findUsuariosMensaisProduto();
    }

    @GetMapping("/avaliacoes-mensais-pacote")
    public List<AvaliacaoMensalPacoteDto> findAvaliacaoMensalPacote() {
        return pacoteService.findAvaliacaoMensalPacote();
    }
}
