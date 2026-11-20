
package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultaCentavosTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void deveRetornarZeroSemAtraso() {
        assertEquals(0, regras.multaCentavos(TipoUsuario.ALUNO, 0));
    }

    @Test
    void deveCobrar100CentavosNoPrimeiroDia() {
        assertEquals(100, regras.multaCentavos(TipoUsuario.ALUNO, 1));
    }

    @Test
    void deveCobrar1000CentavosNoDecimoDia() {
        assertEquals(1000, regras.multaCentavos(TipoUsuario.ALUNO, 10));
    }

    @Test
    void deveCobrar1200CentavosNoDecimoPrimeiroDia() {
        assertEquals(1200, regras.multaCentavos(TipoUsuario.ALUNO, 11));
    }

    @Test
    void deveAtingirTetoDe5000CentavosNoDia30() {
        assertEquals(
                5000,
                regras.multaCentavos(TipoUsuario.ALUNO, 30));
    }

    @Test
    void deveManterTetoDe5000CentavosNoDia31() {
        assertEquals(
                5000,
                regras.multaCentavos(TipoUsuario.ALUNO, 31));
    }

    @Test
    void professorNaoDevePagarNosTresPrimeirosDias() {
        assertEquals(0, regras.multaCentavos(TipoUsuario.PROFESSOR, 3));
    }

    @Test
    void professorDevePagarNoQuartoDia() {
        assertEquals(100, regras.multaCentavos(TipoUsuario.PROFESSOR, 4));
    }

    @Test
    void professorDevePagar1000CentavosNoDia13() {
        assertEquals(1000, regras.multaCentavos(TipoUsuario.PROFESSOR, 13));
    }

    @Test
    void professorDevePagar1200CentavosNoDia14() {
        assertEquals(1200, regras.multaCentavos(TipoUsuario.PROFESSOR, 14));
    }

    @Test
    void visitanteDevePagarDesdePrimeiroDia() {
        assertEquals(100, regras.multaCentavos(TipoUsuario.VISITANTE, 1));
    }

    @Test
    void deveLancarExcecaoParaDiasNegativos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.multaCentavos(TipoUsuario.ALUNO, -1));
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.multaCentavos(null, 1));
    }
}
