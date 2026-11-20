package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PodeRenovarTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void devePermitirAlunoSemRenovacoes() {
        assertTrue(regras.podeRenovar(TipoUsuario.ALUNO, 0, false, false));
    }

    @Test
    void devePermitirAlunoComUmaRenovacao() {
        assertTrue(regras.podeRenovar(TipoUsuario.ALUNO, 1, false, false));
    }

    @Test
    void deveImpedirAlunoComDuasRenovacoes() {
        assertFalse(regras.podeRenovar(TipoUsuario.ALUNO, 2, false, false));
    }

    @Test
    void deveImpedirAlunoComTresRenovacoes() {
        assertFalse(regras.podeRenovar(TipoUsuario.ALUNO, 3, false, false));
    }

    @Test
    void devePermitirProfessorComDuasRenovacoes() {
        assertTrue(regras.podeRenovar(TipoUsuario.PROFESSOR, 2, false, false));
    }

    @Test
    void deveImpedirProfessorComTresRenovacoes() {
        assertFalse(regras.podeRenovar(TipoUsuario.PROFESSOR, 3, false, false));
    }

    @Test
    void deveImpedirProfessorComQuatroRenovacoes() {
        assertFalse(regras.podeRenovar(TipoUsuario.PROFESSOR, 4, false, false));
    }

    @Test
    void deveImpedirVisitanteSemRenovacoes() {
        assertFalse(regras.podeRenovar(TipoUsuario.VISITANTE, 0, false, false));
    }

    @Test
    void deveImpedirRenovacaoQuandoPossuiReserva() {
        assertFalse(regras.podeRenovar(TipoUsuario.ALUNO, 0, true, false));
    }

    @Test
    void deveImpedirRenovacaoQuandoEstaEmAtraso() {
        assertFalse(regras.podeRenovar(TipoUsuario.ALUNO, 0, false, true));
    }

    @Test
    void deveLancarExcecaoParaRenovacoesNegativasDeAluno() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeRenovar(TipoUsuario.ALUNO, -1, false, false));
    }

    @Test
    void deveLancarExcecaoParaRenovacoesNegativasDeProfessor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeRenovar(TipoUsuario.PROFESSOR, -1, false, false));
    }

    @Test
    void deveLancarExcecaoParaRenovacoesNegativasDeVisitante() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeRenovar(TipoUsuario.VISITANTE, -1, false, false));
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeRenovar(null, 0, false, false));
    }
}
