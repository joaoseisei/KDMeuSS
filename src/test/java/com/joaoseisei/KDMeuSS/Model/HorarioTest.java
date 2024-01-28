package com.joaoseisei.KDMeuSS.Model;
import com.joaoseisei.KDMeuSS.Model.Horario;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorarioTest {
    @Test
    void formataHorarioTest() {
        Horario horario = new Horario("35M34");

        //TESTA DIAS
        List<Integer> dia1 = new ArrayList<>(horario.getDia());
        assertEquals(3, dia1.get(0));
        assertEquals(5, dia1.get(1));

        //TESTA HORAS
        List<Integer> hora1 = new ArrayList<>(horario.getHora());
        assertEquals(3, hora1.get(0));
        assertEquals(4, hora1.get(1));

        //TESTA TURNO
        assertEquals("M", horario.getTurno());
    }
    @Test
    void formataHorarioErrorDiaTest() {
        assertThrows(RuntimeException.class, () -> {
            new Horario("8M34");
            new Horario("M34");
        });
    }
    @Test
    void formataHorarioErrorHoraTest() {
        assertThrows(RuntimeException.class, () -> {
            new Horario("3M6");
            new Horario("3M");
        });
    }
    @Test
    void formataHorarioErrorTurnoTest() {
        assertThrows(RuntimeException.class, () -> {
            new Horario("35X34");
            new Horario("3534");
        });
    }
}