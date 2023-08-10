package com.joaoseisei.KDMeuSS;

import Model.Materia;
import java.util.ArrayList;

/**
 * Essa classe será utilizada para adicionar dados ao projeto.
 */
public class Dados {
    public ArrayList<Materia> getMaterias(){

        ArrayList<Materia> memoria = new ArrayList<>();
        memoria.add(new Materia("EDA", "ROSE", "35M34"));
        memoria.add(new Materia("EDA", "NILTON", "35M34"));
        memoria.add(new Materia("EDA", "ROSE", "35T23"));

        memoria.add(new Materia("FAC", "TIAGO", "24M34"));
        memoria.add(new Materia("FAC", "JOHN", "46T45"));

        memoria.add(new Materia("MDS", "HILMER", "35T45"));
        memoria.add(new Materia("MDS", "GEORGE", "35T23"));
        memoria.add(new Materia("MDS", "CARLA", "46M34"));
        memoria.add(new Materia("MDS", "RICARDO", "24T23"));

        memoria.add(new Materia("GPQ", "REJANE", "35T23"));
        memoria.add(new Materia("GPQ", "MARIO", "35T45"));

        memoria.add(new Materia("MD2", "MATHEUS", "26T23"));

        memoria.add(new Materia("PJ1", "FULANO", "24T45"));

        return memoria;
    }
}
