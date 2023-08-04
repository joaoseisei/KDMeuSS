package com.joaoseisei.KDMeuSS;

import Model.Grade;
import Model.Materia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gradesFiltradas")
public class Controle {
    @GetMapping
    public ResponseEntity<List<Grade>> getGrades(){
        ArrayList<Materia> teste = new ArrayList<>();
        ArrayList<Materia> teste2 = new ArrayList<>();
        teste.add(new Materia("introduçao a computaria 1", "teste", "12M12 3M34"));
        teste.add(new Materia("fulano2", "teste2", "12T23"));
        teste2.add(new Materia("LAULAUALUA", "FSDFADSFADS", "1234T234"));
        Grade grade = new Grade(teste);
        Grade grade2 = new Grade(teste2);
        List<Grade> grades = new ArrayList<>();
        grades.add(grade);
        grades.add(grade2);
        return ResponseEntity.ok(grades);
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

