
package br.edu.biblioteca;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataDevolucaoTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();

    @Test
    void deveSomar14DiasParaAluno() {
        LocalDate retirada = LocalDate.of(2026, 11, 20);
        LocalDate esperado = LocalDate.of(2026, 12, 4);

        assertEquals(
            esperado,
            regras.dataDevolucao(retirada, TipoUsuario.ALUNO)
        );
    }

    @Test
    void deveSomar30DiasParaProfessor() {
        LocalDate retirada = LocalDate.of(2026, 11, 20);
        LocalDate esperado = LocalDate.of(2026, 12, 21);

        // 20/12 seria domingo; ajusta para segunda-feira.
        assertEquals(
            esperado,
            regras.dataDevolucao(retirada, TipoUsuario.PROFESSOR)
        );
    }

    @Test
    void deveSomar7DiasParaVisitante() {
        LocalDate retirada = LocalDate.of(2026, 11, 20);
        LocalDate esperado = LocalDate.of(2026, 11, 27);

        assertEquals(
            esperado,
            regras.dataDevolucao(retirada, TipoUsuario.VISITANTE)
        );
    }

    @Test
    void deveManterDevolucaoNoSabado() {
        LocalDate retirada = LocalDate.of(2026, 11, 21);
        LocalDate esperado = LocalDate.of(2026, 12, 5);

        assertEquals(
            esperado,
            regras.dataDevolucao(retirada, TipoUsuario.ALUNO)
        );
    }

    @Test
    void deveAdiarDevolucaoDeDomingoParaSegunda() {
        LocalDate retirada = LocalDate.of(2026, 11, 22);
        LocalDate esperado = LocalDate.of(2026, 12, 7);

        assertEquals(
            esperado,
            regras.dataDevolucao(retirada, TipoUsuario.ALUNO)
        );
    }

    @Test
    void deveLancarExcecaoParaDataNula() {
        assertThrows(
            IllegalArgumentException.class,
            () -> regras.dataDevolucao(null, TipoUsuario.ALUNO)
        );
    }

    @Test
    void deveLancarExcecaoParaTipoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> regras.dataDevolucao(
                LocalDate.of(2026, 11, 20), null
            )
        );
    }
}
