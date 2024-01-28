package com.joaoseisei.KDMeuSS.Control;

import com.joaoseisei.KDMeuSS.Control.GerenciadorGrades;
import com.joaoseisei.KDMeuSS.Model.Materia;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GerenciadorGradesTest {

    ArrayList<Materia> memoria = new ArrayList<>();

    public void populaMaterias(){

        Materia recebeEquivalencia = new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS","35M34");
        recebeEquivalencia.addEquivalente("FGA0173");

        memoria.add(recebeEquivalencia);
        memoria.add(new Materia("FGA0003 - COMPILADORES 1", "LUIS FILOMENO DE JESUS FERNANDES", "46M34"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "ANDRE BARROS DE SALES", "35M12"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "SERGIO ANTONIO ANDRADE DE FREITAS", "24M34"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "REJANE MARIA DA COSTA FIGUEIREDO", "35T45"));
    }

    /**
     * Testa a remoção de materias equivalentes em uma lista de materias.
     */
    @Test
    void verificaEquivalenciaTest(){
        populaMaterias();
        GerenciadorGrades gg = new GerenciadorGrades(memoria);

        ArrayList<Materia> escolhas = new ArrayList<>();
        escolhas.add(new Materia("FGA0003 - COMPILADORES 1", null,null));
        escolhas.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", null,null));

        assertEquals(gg.verificaEquivalencia(escolhas).size(), 1);
    }

    /**
     * Testa a busca de matérias na memoria, incluindo equivalentes.
     */
    @Test
    void possibilidadesTest() {
        populaMaterias();
        GerenciadorGrades gg = new GerenciadorGrades(memoria);

        ArrayList<Materia> escolhas = new ArrayList<>();
        escolhas.add(new Materia("FGA0003 - COMPILADORES 1", null,null));

        ArrayList<ArrayList<Materia>> possibilidades1 = gg.possibilidades(escolhas);
        assertEquals(gg.possibilidades(escolhas).get(0).size(), memoria.size());

        escolhas.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", null,null));
        assertEquals(possibilidades1, gg.possibilidades(gg.verificaEquivalencia(escolhas)));
    }

    @Test
    void filtrarPreferencia() {
    }
//        System.out.println(gg.possibilidades(escolhas));
    @Test
    void gerarGrades() {
        populaMaterias();
        memoria.add(new Materia("FGA0158 - ORIENTAÇÃO A OBJETOS", "JSS", "35T45"));
        GerenciadorGrades gg = new GerenciadorGrades(memoria);

        ArrayList<Materia> escolhas = new ArrayList<>();
        escolhas.add(new Materia("FGA0003 - COMPILADORES 1", null,null));
        escolhas.add(new Materia("FGA0158 - ORIENTAÇÃO A OBJETOS", null,null));
        System.out.println(gg.gerarGrades(escolhas));
    }

    @Test
    void verificarHoras() {
    }

    @Test
    void verificadorConflitosHorarios() {
    }

    @Test
    void verificadorConflitosMaterias() {
    }

    @Test
    void verificadorConflitosArrays() {
    }

    @Test
    void isGradeConflitante() {
    }
}