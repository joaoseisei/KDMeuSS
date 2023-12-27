package com.joaoseisei.KDMeuSS;

import Model.Materia;
import java.util.ArrayList;

/**
 * Essa classe será utilizada para adicionar dados ao projeto.
 */
public class Dados {
    public ArrayList<Materia> getMaterias(){

        ArrayList<Materia> memoria = new ArrayList<>();

        memoria.add(new Materia("FGA0003 - COMPILADORES 1", "BRUNO CESAR RIBAS","35M34"));
        memoria.add(new Materia("FGA0003 - COMPILADORES 1", "LUIS FILOMENO DE JESUS FERNANDES", "46M34"));

        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "ANDRE BARROS DE SALES", "35M12"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "SERGIO ANTONIO ANDRADE DE FREITAS", "24M34"));
        memoria.add(new Materia("FGA0173 - INTERAÇÃO HUMANO COMPUTADOR", "REJANE MARIA DA COSTA FIGUEIREDO", "35T45"));


        return memoria;
    }
}
