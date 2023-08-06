package com.joaoseisei.KDMeuSS;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HtmlController {
    @GetMapping("/")
    public ResponseEntity<String> carregarHTML() throws IOException {
        String localizacao = new String(getClass().getResourceAsStream("/templates/PagInicial.html").readAllBytes());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(localizacao);
    }
    @GetMapping("/gerarGrade")
    public ResponseEntity<String> abrirGrade() throws IOException {
        String localizacao = new String(getClass().getResourceAsStream("/templates/PagGrade.html").readAllBytes());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(localizacao);
    }
    @GetMapping("/gerarFluxo")
    public ResponseEntity<String> abrirFluxo() throws IOException {
        String localizacao = new String(getClass().getResourceAsStream("/templates/PagFluxo.html").readAllBytes());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(localizacao);
    }
}

