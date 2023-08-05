package com.joaoseisei.KDMeuSS;

import Model.Grade;
import Model.Materia;

import Model.Memoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gradesFiltradas")
public class Controle {
    @GetMapping
    public ResponseEntity<List<Grade>> getGrades(){
        Dados dados = new Dados();
        Memoria memoria = new Memoria();
        memoria.setMaterias(dados.getMaterias());

        GerenciadorGrades gerenciadorGrades = new GerenciadorGrades(memoria);

        ArrayList<Materia> teste = new ArrayList<>();
        Materia lau = new Materia("EDA", "nenhum", "nenhum");
        Materia lau1 = new Materia("FAC", "nenhum", "nenhum");
        Materia lau2 = new Materia("MDS", "nenhum", "nenhum");
        Materia lau3 = new Materia("GPQ", "nenhum", "nenhum");
        Materia lau4 = new Materia("MD2", "nenhum", "nenhum");
        Materia lau5 = new Materia("PJ1", "nenhum", "nenhum");
        teste.add(lau);
        teste.add(lau1);
        teste.add(lau2);
        teste.add(lau3);
        teste.add(lau4);
        teste.add(lau5);
        ArrayList<Grade> gradesPossiveis = new ArrayList<>(gerenciadorGrades.gerarGrades(teste));
        System.out.println("Grades Possiveis: "+gradesPossiveis.size());
        return ResponseEntity.ok(gradesPossiveis);
    }

    @PostMapping("/endpoint")
    public ResponseEntity<String> processarJson(@RequestBody Materia materia){
        System.out.println("Nome: " + materia.getNome());
        System.out.println("Professor: " + materia.getProfessor());
        System.out.println("Código: " + materia.getCodigo());
        // Faça o processamento necessário com a Materia
        return ResponseEntity.ok("Materia recebida e processada com sucesso!");
    }
}

