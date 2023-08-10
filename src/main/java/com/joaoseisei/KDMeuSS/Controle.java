package com.joaoseisei.KDMeuSS;

import Model.Grade;
import Model.Materia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gradesFiltradas")
public class Controle {
    Dados dados = new Dados();
    /**
     * Processa a lista de preferências do front.
     * @param materias Lista de matérias recebidas.
     * @return Lista de grades possiveis
     */
    @PostMapping("/preferencias")
    public ResponseEntity<List<Grade>> processarJson(@RequestBody ArrayList<Materia> materias){
        GerenciadorGrades gerenciadorGrades = new GerenciadorGrades(dados.getMaterias());
        return ResponseEntity.ok(gerenciadorGrades.gerarGrades(materias));
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