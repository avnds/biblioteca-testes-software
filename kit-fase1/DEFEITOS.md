
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
