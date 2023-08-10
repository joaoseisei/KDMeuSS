package com.joaoseisei.KDMeuSS;

import Model.Grade;
import Model.Materia;

import Model.Memoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gradesFiltradas")
public class Controle {
    private ArrayList<Materia> preferencias = new ArrayList<>();
    Dados dados = new Dados();
    Memoria memoria = new Memoria();

    /**
     * Envia uma lista de grades possiveis com as preferências.
     * @return OK
     */
    @GetMapping
    public ResponseEntity<List<Grade>> getGrades(){
        memoria.setMaterias(dados.getMaterias());
        GerenciadorGrades gerenciadorGrades = new GerenciadorGrades(memoria);
        return ResponseEntity.ok(gerenciadorGrades.gerarGrades(preferencias));
    }

    /**
     * Processa a lista de preferências do front.
     * @param materias Lista de matérias recebidas.
     * @return OK
     */
    @PostMapping("/preferencias")
    public ResponseEntity<String> processarJson(@RequestBody ArrayList<Materia> materias){
        this.preferencias = materias;
        return ResponseEntity.ok("ok");
    }

    /**
     * Envia uma lista de Materias para /listaProfessores.
     * @return Lista de strings com nome dos professores.
     */
    @GetMapping("/listaMaterias")
    public ResponseEntity<Set<String>> getNomeMaterias(){
        return ResponseEntity.ok(dados.getMaterias().stream().map(Materia::getNome).collect(Collectors.toSet()));
    }

    /**
     * Envia uma lista de professores para /listaProfessores
     * @return Lista de strings com nome dos professores.
     */
    @GetMapping("/listaProfessores")
    public ResponseEntity<Set<String>> getNomeProfessores(){
        return ResponseEntity.ok(dados.getMaterias().stream().map(Materia::getProfessor).collect(Collectors.toSet()));
    }
}