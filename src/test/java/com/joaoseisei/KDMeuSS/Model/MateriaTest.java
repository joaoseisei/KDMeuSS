package com.joaoseisei.KDMeuSS.Model;
import com.joaoseisei.KDMeuSS.Model.Horario;
import com.joaoseisei.KDMeuSS.Model.Materia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MateriaTest {
    Materia materia = new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS","35M34");
    @Test
    void atributosTest(){
        assertEquals("FGA0003 - COMPILADORES 1", materia.getNomeCompleto());
        assertEquals("FGA0003", materia.getChaveDisciplina());
        assertEquals("C1", materia.getSiglaDisciplina());
        assertEquals("BRUNO CESAR RIBAS", materia.getProfessor());
        assertEquals("35M34", materia.getCodigo());
    }
    @Test
    void settersAndGettersTest() {
        Materia materia = new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS", "35M34");

        // Teste setters
        materia.setProfessor("Novo Professor");
        materia.setNota(90);
        materia.addComentarios("Otimo professor");
        materia.addEquivalente("equivalente");
        materia.addPrerequisito("Pré-requisito");

        // Teste getters
        assertEquals("Novo Professor", materia.getProfessor());
        assertEquals(90, materia.getNota());
        assertTrue(materia.getComentarios().contains("Otimo professor"));
        assertTrue(materia.getEquivalentes().contains("equivalente"));
        assertTrue(materia.getPrerequisitos().contains("Pré-requisito"));
    }
    @Test
    void horarioTest(){
        Materia materia2 = new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS", "35M34 4T1");
        assertEquals(1, materia.getHorario().size());
        assertEquals(2, materia2.getHorario().size());

        for(Horario index: materia.getHorario()) {
            assertEquals("DIA: [3, 5] | HORA: [3, 4] | TURNO: M", index.toString());
        }
    }
    @Test
    void equalsTest(){
        Materia materia2 = new Materia("FGA0003 - COMPILADORES 1", null, null);
        Materia materia3 = new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS", null);
        Materia materia4 = new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS", "35M34");

        assertTrue(materia2.equals(materia));

        assertTrue(materia.equals(materia2));
        assertTrue(materia.equals(materia3));
        assertTrue(materia.equals(materia4));
    }
    @Test
    void nomeDisciplinaErrorTest(){
        assertThrows(RuntimeException.class, () -> {
            new Materia("COMPILADORES 1", "BRUNO CESAR RIBAS", "8M34");
            new Materia("FGA0003", "BRUNO CESAR RIBAS", "8M34");
        });
    }
}