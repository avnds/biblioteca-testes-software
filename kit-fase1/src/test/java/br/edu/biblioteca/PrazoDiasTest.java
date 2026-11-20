
package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrazoDiasTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void deveRetornar14DiasParaAluno() {
        int resultado = regras.prazoDias(TipoUsuario.ALUNO);

        assertEquals(14, resultado);
    }

    @Test
    void deveRetornar30DiasParaProfessor() {
        int resultado = regras.prazoDias(TipoUsuario.PROFESSOR);

        assertEquals(30, resultado);
    }

    @Test
    void deveRetornar7DiasParaVisitante() {
        int resultado = regras.prazoDias(TipoUsuario.VISITANTE);

        assertEquals(7, resultado);
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> regras.prazoDias(null)
        );
    }
}
