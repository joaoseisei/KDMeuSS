package Model;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Utilidades {
    public static final Pattern patternHorario = Pattern.compile("^(\\d+)([MTN])(\\d+)$");
    public static final Pattern patternDisciplina = Pattern.compile("(\\S+) - (.+)");
    public static String simplificarNomeMateria(String nomeCompleto) {
        if (nomeCompleto != null && !nomeCompleto.isEmpty()) {
            return Arrays.stream(nomeCompleto.split("\\s+"))
                    .filter(index -> (index.length() == 1 || index.length() > 3) && !index.equalsIgnoreCase("PARA"))
                    .map(index -> Character.toUpperCase(index.charAt(0)))
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
        }return "";
    }

}
