
# Relatório de Defeitos — Fase 1

## DEF-001 — Prazo incorreto para visitante

**Método:** `prazoDias(TipoUsuario tipo)`

**Entrada:** `TipoUsuario.VISITANTE`

**Técnica:** Particionamento de equivalência.

**Classe de equivalência:** Válida — VISITANTE.

**Teste:** `PrazoDiasTest.deveRetornar7DiasParaVisitante`

**Resultado esperado:** 7 dias.

**Resultado obtido:** 10 dias.

**Descrição:**
Ao consultar o prazo de empréstimo para um usuário
do tipo VISITANTE, a biblioteca retorna 10 dias,
contrariando a especificação, que determina 7 dias.

**Evidência:**
expected: <7> but was: <10>

**Status:** Defeito reproduzido por teste automatizado.

---

## DEF-002 — Data de devolução em domingo não ajustada

**Método:** `dataDevolucao(LocalDate retirada, TipoUsuario tipo)`

**Entrada:** `LocalDate.of(2026, 11, 20), TipoUsuario.PROFESSOR`

**Técnica:** Análise de valor-limite — transição domingo/segunda-feira.

**Teste:** `DataDevolucaoTest.deveSomar30DiasParaProfessor`

**Resultado esperado:** 2026-12-21 (segunda-feira).

**Resultado obtido:** 2026-12-20 (domingo).

**Descrição:**
O prazo de 30 dias termina em um domingo, mas a
implementação não transfere a devolução para a
segunda-feira seguinte, conforme a especificação.

**Evidência:**
expected: <2026-12-21> but was: <2026-12-20>

**Status:** Defeito reproduzido por teste automatizado.

---

## DEF-003 — Data de devolução incorreta para visitante

**Método:** `dataDevolucao(LocalDate retirada, TipoUsuario tipo)`

**Entrada:** `LocalDate.of(2026, 11, 20), TipoUsuario.VISITANTE`

**Técnica:** Particionamento de equivalência.

**Classe de equivalência:** Válida — VISITANTE.

**Teste:** `DataDevolucaoTest.deveSomar7DiasParaVisitante`

**Resultado esperado:** 2026-11-27.

**Resultado obtido:** 2026-11-30.

**Descrição:**
A biblioteca calcula uma data de devolução três dias
posterior à prevista para o tipo VISITANTE.

**Evidência:**
expected: <2026-11-27> but was: <2026-11-30>

**Status:** Defeito reproduzido por teste automatizado.
