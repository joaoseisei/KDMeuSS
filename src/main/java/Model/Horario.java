package Model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Essa classe é o molde para transformar o código de uma matéria da UNB(12M12) em um formato processável.
 */
public class Horario {
    //Pattern para tratar os horários, caso precise reutilizar.
    public static final Pattern pattern = Pattern.compile("^(\\d+)([MTN])(\\d+)$");
    private Set<Integer> dia = new HashSet<>();      //Lista de dias da matéria (2 = segunda, 3 = terça...)
    private Set<Integer> hora = new HashSet<>();     //Lista de horário da matéria (1 = primeiro horário...)
    private String turno = "";                       //Lista de turnos da matéria (M = manha...)
//CONSTRUTOR
    /**
     * Cria um objeto Horário a partir de um código.
     * @param codigo Código a ser tratado.
     */
    public Horario(String codigo){
        formatador(codigo);
    }
//GETTERS
    public Set<Integer> getDia(){
        return dia;
    }
    public Set<Integer> getHora(){
        return hora;
    }
    public String getTurno(){
        return turno;
    }
//METODOS
    /**
     * Formata o código para extrair informações sobre dias, horas e turno.
     * @param codigo Código a ser formatado.
     */
    public void formatador(String codigo){
        Matcher matcher = pattern.matcher(codigo);
        if (matcher.matches()){
            String dias = matcher.group(1);
                for(char index : dias.toCharArray()) dia.add(Character.getNumericValue(index));
            String horas = matcher.group(3);
                for(char index : horas.toCharArray()) hora.add(Character.getNumericValue(index));
            this.turno = turno + matcher.group(2);
        }
    }
//TOSTRING
    public String toString() {
        return "DIA: " + dia + " | HORA: " + hora + " | TURNO: " + turno;
    }
}