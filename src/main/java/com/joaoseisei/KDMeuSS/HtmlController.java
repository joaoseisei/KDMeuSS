package com.joaoseisei.KDMeuSS;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;

/**
 * Essa classe é responsável por abrir os htmls.
 */
@Controller
public class HtmlController {
    /**
     * Carrega a página inicial.
     * @return conteudo da página.
     * @throws IOException Página não encontrada.
     */
    @GetMapping("/")
    public ResponseEntity<String> abrirInicio() throws IOException {
        String localizacao = new String(getClass().getResourceAsStream("/templates/PagInicial.html").readAllBytes());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(localizacao);
    }

    /**
     * Carrega a página de grades.
     * @return Conteudo da página.
     * @throws IOException Página não encontrada.
     */
    @GetMapping("/gerarGrade")
    public ResponseEntity<String> abrirGrade() throws IOException {
        String localizacao = new String(getClass().getResourceAsStream("/templates/PagGrade.html").readAllBytes());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(localizacao);
    }

    /**
     * Carrega a página de fluxos.
     * @return Conteudo da página.
     * @throws IOException Página não encontrada.
     */
    @GetMapping("/gerarFluxo")
    public ResponseEntity<String> abrirFluxo() throws IOException {
        String localizacao = new String(getClass().getResourceAsStream("/templates/PagFluxo.html").readAllBytes());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(localizacao);
    }
}

