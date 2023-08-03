package Model;

import java.util.ArrayList;

/**
 * Representa uma memória para armazenar disciplinas do currículo da UNB.
 */
public class Memoria {
    private ArrayList<Materia> materias = new ArrayList<>();
//GETTERS
    public ArrayList<Materia> getMaterias(){
        return materias;
    }
//SETTERS
    public void setMaterias(ArrayList<Materia> novaMaterias){
        this.materias = novaMaterias;
    }
//METODOS
    /**
     * Adiciona uma nova matéria à lista de matérias na memória.
     * @param nome O nome da matéria a ser adicionada.
     * @param professor O nome do professor responsável pela matéria a ser adicionada.
     * @param codigo O código da matéria a ser adicionada(23M23).
     */
    public void addMateria(String nome, String professor, String codigo){
        materias.add(new Materia(nome, professor, codigo));
    }
}
