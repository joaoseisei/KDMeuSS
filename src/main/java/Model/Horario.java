package Model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Essa classe é o molde para transformar o código de uma matéria da UNB(12M12) em um formato processável.
 */
public class Horario {
    private Set<Integer> dia = new HashSet<>();      //Lista de dias da matéria (2 = segunda, 3 = terça...)
    private Set<Integer> hora = new HashSet<>();     //Lista de horário da matéria (1 = primeiro horário...)
    private String turno = "";                       //Lista de turnos da matéria (M = manha...)
    public Horario(String codigo){
        formatador(codigo);
    }
    public Set<Integer> getDia(){
        return dia;
    }
    public Set<Integer> getHora(){
        return hora;
    }
    public String getTurno(){
        return turno;
    }
    public void formatador(String codigo){
        Matcher matcher = Utilidades.patternHorario.matcher(codigo);
        if (matcher.matches()){
            String dias = matcher.group(1);
                for(char index : dias.toCharArray()) dia.add(Character.getNumericValue(index));
            String horas = matcher.group(3);
                for(char index : horas.toCharArray()) hora.add(Character.getNumericValue(index));
            this.turno = turno + matcher.group(2);
        }
    }
    @Override
    public String toString() {
        return "DIA: " + dia + " | HORA: " + hora + " | TURNO: " + turno;
    }
}