package Model;

import java.util.HashSet;
import java.util.Set;

public class Professor{
    private String nome;
    private Set<Materia> materias = new HashSet<>();
    private Integer notaMedia;
    public Professor(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public Set<Materia> getMaterias(){
        return materias;
    }
    public Integer getNotaMedia() {
        return notaMedia;
    }
    public void addMateria(Materia materia){
        this.materias.add(materia);
        atualizaNota();
    }
    private void atualizaNota(){
        int nota = 0;
        for(Materia index: materias) nota += index.getNota();
        this.notaMedia = (materias.isEmpty()) ? null : nota / materias.size();
    }
    @Override
    public String toString() {
        return String.format("Professor: %s | Nota Média: %s", nome, notaMedia);
    }
}
