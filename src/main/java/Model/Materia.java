package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa uma disciplina da UNB.
 */
public class Materia {
    private String nome;
    private String professor;
    private String codigo;
    private Set<Horario> horario = new HashSet<>();
    private List<String> idGrade = new ArrayList<>();
//CONSTRUTOR
    /**
     * Construtor da classe Materia.
     * @param nome O nome da disciplina.
     * @param professor O nome do professor responsável pela disciplina.
     * @param codigo O código da disciplina(12M12).
     */
    public Materia(String nome, String professor, String codigo){
        if(nome!=null) this.nome = nome;
        if(professor!=null)this.professor = professor;
        if(codigo!=null){
            this.codigo = codigo;

            String[] partes = codigo.split("\\s+");
            for(String index : partes){
                horario.add(new Horario(index));
            }
            criarID(horario);
        }
    }
//GETTERS
    public String getNome(){
        return nome;
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
    public List<String> getIdGrade(){
        return idGrade;
    }
//SETTERS
    public void setProfessor(String novoProfessor){
        this.professor = novoProfessor;
    }
//METODOS

    /**
     * Override de equals que verifica se uma matéria é igual a outra com base nos atributos.
     * @param obj Matéria a ser comparada.
     * @return retorna true se as matérias são iguais.
     */
    public boolean equals(Materia obj){
        return this.nome.equals(obj.getNome()) &&
                verificarProfessor(obj) && verificarCodigo(obj);
    }

    /**
     * Esse método compara 2 atributos de matéria(professor), caso o professor seja "nenhum" retorna true.
     * @param obj Matéria a ser comparada.
     * @return retorna true se os professores forem iguais.
     */
    public boolean verificarProfessor(Materia obj){
        return !(obj.getProfessor().equals("nenhum") || obj.getProfessor().equals(this.professor));
    }

    /**
     * Esse método compara 2 atributos de matéria(código), caso o código seja "nenhum" retorna true.
     * @param obj Matéria a ser comparada.
     * @return retorna true se os códigos forem iguais.
     */
    public boolean verificarCodigo(Materia obj){
        return !(obj.getProfessor().equals("nenhum") || obj.getProfessor().equals(this.professor));
    }

    /**
     * Esse método transforma o horario em uma lista de ids para colocar nas células HTML.
     * @param horario Lista de horários a ser transformado em ID
     */
    public void criarID(Set<Horario> horario){
        for(Horario index : horario){
            for(Integer dia : index.getDia()){
                for(Integer hora : index.getHora()){
                    idGrade.add(index.getTurno()+hora+dia);
                }
            }
        }
    }
//TOSTRING
    public String toString(){
        return "\nMateria: "+nome+" | Professor: "+professor+" | Codigo: "+codigo;
    }
}
