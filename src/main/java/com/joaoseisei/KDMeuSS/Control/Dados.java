package com.joaoseisei.KDMeuSS.Control;

import com.joaoseisei.KDMeuSS.Model.Materia;
import java.util.ArrayList;

/**
 * Essa classe será utilizada para adicionar dados ao projeto.
 */
public class Dados {
    public ArrayList<Materia> getMaterias(){

        ArrayList<Materia> memoria = new ArrayList<>();

        memoria.add(new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS","35M34"));
        memoria.add(new Materia("FGA0003 - COMPILADORES 1", "LUIS FILOMENO DE JESUS FERNANDES", "46M34"));

        memoria.add(new Materia("FGA0170 - FUNDAMENTOS DE SISTEMAS OPERACIONAIS", "DANIEL SUNDFELD LIMA", "24M5 24T1"));

        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "ANDRE BARROS DE SALES", "35M12"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "SERGIO ANTONIO ANDRADE DE FREITAS", "24M34"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "REJANE MARIA DA COSTA FIGUEIREDO", "35T45"));

        memoria.add(new Materia("FGA0137 - SISTEMAS DE BANCO DE DADOS 1", "VANDOR ROBERTO", "35T23"));
        memoria.add(new Materia("FGA0137 - SISTEMAS DE BANCO DE DADOS 1", "MAURICIO SERRANO", "26M12"));

        memoria.add(new Materia("FGA0030 - ESTRUTURAS DE DADOS 2", "JOHN LENON CARDOSO", "46T45"));
        memoria.add(new Materia("FGA0030 - ESTRUTURAS DE DADOS 2", "JOHN LENON CARDOSO", "46M34"));

        memoria.add(new Materia("FGA0172 - REQUISITOS DE SOFTWARE", "ANDRE BARROS DE SALES", "35M34"));
        memoria.add(new Materia("FGA0172 - REQUISITOS DE SOFTWARE", "GEORGE MARSICANO", "35M34"));
        memoria.add(new Materia("FGA0172 - REQUISITOS DE SOFTWARE", "GEORGE MARSICANO", "35M12"));

        memoria.add(new Materia("FGA0238 - TESTES DE SOFTWARE", "ELAINE VENSON", "35T23"));
        memoria.add(new Materia("FGA0238 - TESTES DE SOFTWARE", "ELAINE VENSON", "35T45"));
        return memoria;
    }
}
