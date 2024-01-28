package com.joaoseisei.KDMeuSS.Model;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Utilidades {
    public static final Pattern patternHorario = Pattern.compile("^([1-7]+)(?i)([MTN])([1-5]+)$");
    public static final Pattern patternDisciplina = Pattern.compile("(\\S+) - (.+)");
    private static final Set<String> ligacoes = new HashSet<>(Arrays.asList("para", "de", "dos", "e", "a"));
    public static String simplificarNomeMateria(String nomeCompleto) {
        if (nomeCompleto != null && !nomeCompleto.isEmpty()) {
            return Arrays.stream(nomeCompleto.split("\\s+"))
                    .filter(index -> !ligacoes.contains(index.toLowerCase()))
                    .map(index -> Character.toUpperCase(index.charAt(0)))
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
        }
        return "";
    }
}
