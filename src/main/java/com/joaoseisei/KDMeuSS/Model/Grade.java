package com.joaoseisei.KDMeuSS.Model;
import java.util.ArrayList;

/**
 * Representa uma grade de matérias contendo uma lista de matérias.
 * @param materias Lista de matérias a serem adicionadas.
 */
public record Grade(ArrayList<Materia> materias){}