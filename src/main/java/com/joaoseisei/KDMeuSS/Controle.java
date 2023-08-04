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
        teste.add(new Materia("fulano", "teste", "12M12"));
        teste.add(new Materia("fulano2", "teste2", "12T23"));
        Grade grade = new Grade(teste);
        Grade grade2 = new Grade(teste);
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

