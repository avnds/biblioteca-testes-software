
package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LimiteEmprestimosTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void devePermitir3EmprestimosParaAluno() {
        assertEquals(3, regras.limiteEmprestimos(TipoUsuario.ALUNO));
    }

    @Test
    void devePermitir8EmprestimosParaProfessor() {
        assertEquals(8, regras.limiteEmprestimos(TipoUsuario.PROFESSOR));
    }

    @Test
    void devePermitir1EmprestimoParaVisitante() {
        assertEquals(1, regras.limiteEmprestimos(TipoUsuario.VISITANTE));
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> regras.limiteEmprestimos(null)
        );
    }
}
