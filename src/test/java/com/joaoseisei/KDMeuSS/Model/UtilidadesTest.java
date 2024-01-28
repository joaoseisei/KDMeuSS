package com.joaoseisei.KDMeuSS.Model;

import com.joaoseisei.KDMeuSS.Model.Utilidades;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilidadesTest {
    @Test
    void simplificarNomeMateria() {
        assertEquals("C1", Utilidades.simplificarNomeMateria("COMPILADORES 1"));
        assertEquals("C1", Utilidades.simplificarNomeMateria("COMPILADORES DE PARA DOS 1"));
        assertEquals("IHC", Utilidades.simplificarNomeMateria("INTERAÇÃO HUMANO COMPUTADOR"));
        assertEquals("MNE", Utilidades.simplificarNomeMateria("MÉTODOS NUMÉRICOS PARA ENGENHARIA"));
        assertEquals("", Utilidades.simplificarNomeMateria(""));
    }
}