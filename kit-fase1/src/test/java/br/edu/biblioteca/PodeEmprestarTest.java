package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PodeEmprestarTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void devePermitirAlunoComDoisEmprestimos() {
        assertTrue(regras.podeEmprestar(TipoUsuario.ALUNO, 2, 0, false));
    }

    @Test
    void deveImpedirAlunoComTresEmprestimos() {
        assertFalse(regras.podeEmprestar(TipoUsuario.ALUNO, 3, 0, false));
    }

    @Test
    void deveImpedirAlunoComQuatroEmprestimos() {
        assertFalse(regras.podeEmprestar(TipoUsuario.ALUNO, 4, 0, false));
    }

    @Test
    void devePermitirProfessorComSeteEmprestimos() {
        assertTrue(regras.podeEmprestar(TipoUsuario.PROFESSOR, 7, 0, false));
    }

    @Test
    void deveImpedirProfessorComOitoEmprestimos() {
        assertFalse(regras.podeEmprestar(TipoUsuario.PROFESSOR, 8, 0, false));
    }

    @Test
    void deveImpedirProfessorComNoveEmprestimos() {
        assertFalse(regras.podeEmprestar(TipoUsuario.PROFESSOR, 9, 0, false));
    }

    @Test
    void devePermitirVisitanteSemEmprestimos() {
        assertTrue(regras.podeEmprestar(TipoUsuario.VISITANTE, 0, 0, false));
    }

    @Test
    void deveImpedirVisitanteComUmEmprestimo() {
        assertFalse(regras.podeEmprestar(TipoUsuario.VISITANTE, 1, 0, false));
    }

    @Test
    void deveImpedirVisitanteComDoisEmprestimos() {
        assertFalse(regras.podeEmprestar(TipoUsuario.VISITANTE, 2, 0, false));
    }

    @Test
    void devePermitirEmprestimoComMultaDe999Centavos() {
        assertTrue(regras.podeEmprestar(TipoUsuario.ALUNO, 0, 999, false));
    }

    @Test
    void devePermitirEmprestimoComMultaDe1000Centavos() {
        assertTrue(regras.podeEmprestar(TipoUsuario.ALUNO, 0, 1000, false));
    }

    @Test
    void deveImpedirEmprestimoComMultaDe1001Centavos() {
        assertFalse(regras.podeEmprestar(TipoUsuario.ALUNO, 0, 1001, false));
    }

    @Test
    void deveImpedirEmprestimoQuandoPossuiAtraso() {
        assertFalse(regras.podeEmprestar(TipoUsuario.ALUNO, 0, 0, true));
    }

    @Test
    void deveLancarExcecaoParaEmprestimosAtivosNegativos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeEmprestar(TipoUsuario.ALUNO, -1, 0, false));
    }

    @Test
    void deveLancarExcecaoParaMultaPendenteNegativa() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeEmprestar(TipoUsuario.ALUNO, 0, -1, false));
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> regras.podeEmprestar(null, 0, 0, false));
    }
}
