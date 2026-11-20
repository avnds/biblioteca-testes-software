package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultaTotalCentavosTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void deveRetornarZeroParaListaVazia() {
        assertEquals(
                0,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{}));
    }

    @Test
    void deveRetornarZeroParaEmprestimoSemAtraso() {
        assertEquals(
                0,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{0}));
    }

    @Test
    void deveCobrarMultaParaUmDiaDeAtraso() {
        assertEquals(
                100,
                regras.multaTotalCentavos(TipoUsuario.VISITANTE, new int[]{1}));
    }

    @Test
    void deveSomarMultasDeVariosEmprestimos() {
        assertEquals(
                300,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{0, 1, 2}));
    }

    @Test
    void deveCobrarTarifaProgressivaPorEmprestimo() {
        assertEquals(
                2200,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{10, 11}));
    }

    @Test
    void deveAplicarCarenciaACadaEmprestimoDoProfessor() {
        assertEquals(
                200,
                regras.multaTotalCentavos(TipoUsuario.PROFESSOR, new int[]{3, 4, 4}));
    }

    @Test
    void deveManterMultaIndividualAbaixoDoTeto() {
        assertEquals(
                4800,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{29}));
    }

    @Test
    void deveAtingirTetoIndividual() {
        assertEquals(
                5000,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{30}));
    }

    @Test
    void deveLimitarMultaIndividualAntesDeSomar() {
        assertEquals(
                5100,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{31, 1}));
    }

    @Test
    void deveManterTotalAbaixoDoTeto() {
        assertEquals(
                9900,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{0, 30, 29, 1}));
    }

    @Test
    void deveAtingirTetoTotal() {
        assertEquals(
                10000,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{0, 30, 30}));
    }

    @Test
    void deveLimitarTotalAcimaDoTeto() {
        assertEquals(
                10000,
                regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{0, 30, 30, 1}));
    }

    @Test
    void deveLancarExcecaoParaElementoNegativo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(TipoUsuario.ALUNO, new int[]{1, -1, 2}));
    }

    @Test
    void deveLancarExcecaoParaListaNula() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(TipoUsuario.ALUNO, null));
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(null, new int[]{1}));
    }
}
