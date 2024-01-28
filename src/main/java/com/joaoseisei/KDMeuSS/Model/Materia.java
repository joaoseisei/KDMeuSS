package com.joaoseisei.KDMeuSS.Model;

import java.util.*;
import java.util.regex.Matcher;

/**
 * Representa uma disciplina da UNB.
 */
public class Materia {
    private String chaveDisciplina, siglaDisciplina, nomeCompleto, codigo, professor;
    private Set<Horario> horario = new HashSet<>();
    private Set<String> comentarios = new HashSet<>();
    private Set<String> equivalentes = new HashSet<>();
    private Set<String> prerequisitos = new HashSet<>();
    private Integer nota;
    public Materia(String nomeCompleto, String professor, String codigo) {
        this.nomeCompleto = nomeCompleto;
        this.professor = professor;
        this.codigo = codigo;
        if(codigo != null){
            Arrays.stream(codigo.split("\\s+")).forEach(index -> horario.add(new Horario(index)));
        }
        if(nomeCompleto != null){
            Matcher matcher = Utilidades.patternDisciplina.matcher(nomeCompleto);
            if(matcher.matches()){
                this.chaveDisciplina = matcher.group(1);
                this.siglaDisciplina = Utilidades.simplificarNomeMateria(matcher.group(2));
                return;
            }
            throw new RuntimeException("ERRO AO ANALISAR O NOME COMPLETO DA DISCIPLINA");
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
    public Set<String> getEquivalentes(){
        return equivalentes;
    }
    public Set<String> getPrerequisitos(){
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
    public void addEquivalente(String materia){
        this.equivalentes.add(materia);
    }
    public void addPrerequisito(String materia){
        this.prerequisitos.add(materia);
    }
    public boolean verificaEquivalencia(Materia materia) {
        return equivalentes.stream()
                .anyMatch(index -> index.equals(materia.getChaveDisciplina()));
    }
    public boolean equals(Materia materia){
        return this.chaveDisciplina.equals(materia.getChaveDisciplina()) &&
                (this.professor==null || materia.getProfessor() == null || this.professor.equals(materia.getProfessor())) &&
                (this.codigo==null || materia.getCodigo() == null || this.codigo.equals(materia.getCodigo()));
    }
    @Override
    public String toString(){
        return "Materia: " + nomeCompleto + " | Chave: " + chaveDisciplina + " | Sigla: " + siglaDisciplina + "\n"
                + "Professor: " + professor + " | Codigo: " + codigo + " | Nota: " + nota + "\n"
                + "Equivalencias:\t" +  equivalentes.toString() + "\n"
                + "Pre-requisitos:\t" + prerequisitos.toString() + "\n\n";
    }
}