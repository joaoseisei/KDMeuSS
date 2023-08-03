package com.joaoseisei.KDMeuSS;

import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Classe responsável por controlar as operações relacionadas à geração de grades de horários.
 */
public class GerenciadorGrades {
    private final Memoria memoria;
//CONSTRUTOR
    /**
     * Construtor da classe Controle.
     * @param memoria A memória que contém as matérias disponíveis.
     */
    public GerenciadorGrades(Memoria memoria){
        this.memoria = memoria;
    }
//----------------------------------GERAÇAO DE GRADES------------------------------------
    /**
     * Obtém todas as possibilidades de combinação de matérias selecionadas contidas na memória, filtrando por preferência de professor e o horário.
     * @param escolhidas Lista de matérias com as preferências selecionadas.
     * @return Uma lista de listas de matérias, separadas em blocos com o nome delas.
     */
    public ArrayList<ArrayList<Materia>> possibilidades(ArrayList<Materia> escolhidas) {
        return escolhidas.stream()
                .map(this::filtrarPreferencia)
                .map(codigoDiciplinas -> new ArrayList<>(codigoDiciplinas.values()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filtra a matéria escolhida no parâmetro com base dos atributos dela, se encontrar 2 matérias com mesmo nome e horário, agrupa em uma matéria única.
     * @param materia A matéria escolhida pelo usuário.
     * @return Um mapa com os códigos das matérias como chave e a matéria como valor.
     */
    public HashMap<String, Materia> filtrarPreferencia(Materia materia){
        HashMap<String, Materia> codigoDiciplinas = new HashMap<>();
        memoria.getMaterias().stream().filter(materia::equals) //Entrando no objeto escolhido da memória
                .forEach(materiaAtual -> {
                    String chaveHorario = materiaAtual.getCodigo();
                    if (codigoDiciplinas.containsKey(chaveHorario)) {
                        Materia materiaExistente = codigoDiciplinas.get(chaveHorario);
                        materiaExistente.setProfessor(materiaExistente.getProfessor() + "|" + materiaAtual.getProfessor());
                    }else codigoDiciplinas.put(chaveHorario, new Materia(materiaAtual.getNome(), materiaAtual.getProfessor(), materiaAtual.getCodigo()));
                });
        return codigoDiciplinas;
    }

    /**
     * Esse método é responsável por receber o array de arrays do método possibilidades e transformar e grades através da regra do tracinho (MD1).
     *
     * @param indiceAtual Indicie atual (1°, 2°...).
     * @param combinacaoAtual Combinação de grade atual.
     * @param dados Conjuntos de materias a serem combinadas (dados a serem combinados).
     * @param grades Resultado final com a lista de grades obtidas.
     */
    private void gerarCombinacoes(int indiceAtual, ArrayList<Materia> combinacaoAtual, ArrayList<ArrayList<Materia>> dados, ArrayList<Grade> grades){
        if (indiceAtual == dados.size()) {
            ArrayList<Materia> gradeAtual = new ArrayList<>(combinacaoAtual);
            if(isGradeConflitante(gradeAtual)) grades.add(new Grade(gradeAtual));
            return;
        }
        for (Materia index : dados.get(indiceAtual)) {
            combinacaoAtual.add(index);
            gerarCombinacoes(indiceAtual + 1, combinacaoAtual, dados, grades);
            combinacaoAtual.remove(combinacaoAtual.size() - 1);
        }
    }

    /**
     * Gera todas as possíveis combinações de grades de horários, considerando as matérias escolhidas pelo aluno.
     * @param escolhidas Lista de matérias com as preferências selecionadas.
     * @return Lista de grades sem nenhum conflito com as preferências setadas.
     */
    public ArrayList<Grade> gerarGrades(ArrayList<Materia> escolhidas) {
        ArrayList<Grade> grades = new ArrayList<>();
        gerarCombinacoes(0, new ArrayList<>(), possibilidades(escolhidas), grades);
        return grades;
    }
//-------------------------------VERIFICAÇAO DE CONFLITOS---------------------------------
    /**
     * Verifica se há conflitos de horas em 2 listas de horas.
     * @param primeiroHorario A primeira lista de horários a ser comparado.
     * @param segundoHorario A segunda lista de horários a ser comparado.
     * @return retorna true caso tenha conflito de horário e false caso não esteja conflitando.
     */
    public boolean verificarHoras(Set<Integer> primeiroHorario, Set<Integer> segundoHorario) {
        List<Integer> horario1 = new ArrayList<>(primeiroHorario);
        List<Integer> horario2 = new ArrayList<>(segundoHorario);

        return horario1.get(0) <= horario2.get(horario2.size() - 1) &&
                horario1.get(horario1.size() - 1) >= horario2.get(0);
    }

    /**
     * Verifica se há conflitos entre dois horários.
     * @param primeira O primeiro horário a ser comparado.
     * @param segunda O segundo horário a ser comparado.
     * @return True se os horários tiverem conflitos, false caso contrário.
     */
    public boolean verificadorConflitosHorarios(Horario primeira, Horario segunda) {
        if (!primeira.getTurno().equals(segunda.getTurno())) return false;

        List<Integer> diasConflitantes = new ArrayList<>(primeira.getDia());
        diasConflitantes.retainAll(segunda.getDia());
        if(diasConflitantes.size() < 1) return false;

        return verificarHoras(primeira.getHora(), segunda.getHora());
    }

    /**
     * Verifica se há conflitos entre duas matérias.
     * @param primeira A primeira matéria a ser comparada.
     * @param segunda A segunda matéria a ser comparada.
     * @return True se houver conflitos entre os horários das duas matérias, false caso contrário.
     */
    public boolean verificadorConflitosMaterias(Materia primeira, Materia segunda){
        return primeira.getHorario().stream()
                .flatMap(primeiroHorario -> segunda.getHorario().stream()
                    .filter(segundoHorario -> verificadorConflitosHorarios(primeiroHorario, segundoHorario)))
                    .findAny().isPresent();
    }

    /**
     * Verifica se há conflitos entre os horários de um conjunto de matérias.
     * @param materias A lista de matérias a serem verificadas.
     * @return True se houver conflitos entre os horários de pelo menos duas matérias, false caso contrário.
     */
    public boolean verificadorConflitosArrays(List<Materia> materias) {
        int numMaterias = materias.size();
        return IntStream.range(0, numMaterias - 1)
                .anyMatch(i -> IntStream.range(i + 1, numMaterias)
                        .anyMatch(j -> verificadorConflitosMaterias(materias.get(j), materias.get(i))));
    }

    /**
     * Verifica se uma grade de matérias possui conflitos de horários.
     * @param gradeAtual A lista de matérias que compõem a grade a ser verificada.
     * @return retorna true se nao tiver conflitos, e false se tiver
     */
    public boolean isGradeConflitante(List<Materia> gradeAtual){
        List<Materia> grade = new ArrayList<>(gradeAtual);
        Set<String> codigosAtuais = grade.stream().map(Materia::getCodigo).collect(Collectors.toSet());
        return grade.size() == codigosAtuais.size() && !verificadorConflitosArrays(grade);
    }
}
