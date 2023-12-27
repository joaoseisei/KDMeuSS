package Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Representa uma disciplina da UNB.
 */
public class Materia {
    private String chaveDisciplina, siglaDisciplina, nomeCompleto, codigo, professor;
    private Set<Horario> horario = new HashSet<>();
    private Set<String> comentarios = new HashSet<>();
    private Set<Materia> equivalentes = new HashSet<>();
    private Set<Materia> prerequisitos = new HashSet<>();
    private Integer nota;
    public Materia(String nomeCompleto, String professor, String codigo) {
        this.nomeCompleto = nomeCompleto;
        this.professor = professor;
        this.codigo = codigo;

        if(nomeCompleto != null){
            Matcher matcher = Utilidades.patternDisciplina.matcher(nomeCompleto);
            if(matcher.matches()){
                this.chaveDisciplina = matcher.group(1);
                this.siglaDisciplina = Utilidades.simplificarNomeMateria(matcher.group(2));
            }
        }

        if(codigo != null){
            Arrays.stream(codigo.split("\\s+")).forEach(index -> horario.add(new Horario(index)));
        }
    }
    public String getChaveDisciplina(){
        return chaveDisciplina;
    }
    public String getSiglaDisciplina(){
        return siglaDisciplina;
    }
    public String getNomeCompleto(){
        return nomeCompleto;
    }
    public String getProfessor(){
        return professor;
    }
    public String getCodigo(){
        return codigo;
    }
    public Set<Horario> getHorario(){
        return horario;
    }
    public Integer getNota(){
        return nota;
    }
    public Set<String> getComentarios(){
        return comentarios;
    }
    public Set<Materia> getEquivalentes(){
        return equivalentes;
    }
    public Set<Materia> getPrerequisitos(){
        return prerequisitos;
    }
    public void setProfessor(String novoProfessor){
        this.professor = novoProfessor;
    }
    public void setNota(Integer nota){
        this.nota = nota;
    }
    public void addComentarios(String comentario){
        this.comentarios.add(comentario);
    }
    public void addEquivalente(Materia materia){
        this.equivalentes.add(materia);
    }
    public void addPrerequisito(Materia materia){
        this.prerequisitos.add(materia);
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Materia materia = (Materia) obj;
        return Objects.equals(this.chaveDisciplina, materia.chaveDisciplina) &&
                (this.professor == null || this.professor.equals(materia.professor)) &&
                (this.codigo == null || this.codigo.equals(materia.codigo));
    }
    @Override
    public String toString(){
        return "Materia: " + nomeCompleto + " | Chave: " + chaveDisciplina + " | Sigla: " + siglaDisciplina + "\n"
                + "Professor: " + professor + " | Codigo: " + codigo + " | Nota: " + nota + "\n";
    }
}