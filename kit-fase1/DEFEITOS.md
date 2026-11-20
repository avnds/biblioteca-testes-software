
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

---

## DEF-004 — Tarifa incorreta no 11º dia cobrado

**Método:** multaCentavos(TipoUsuario tipo, int diasAtraso)

**Entrada:** ALUNO, 11 dias de atraso.

**Técnica:** Análise de valores-limite.

**Teste:** deveCobrar1200CentavosNoDecimoPrimeiroDia

**Resultado esperado:** 1200 centavos.

**Resultado obtido:** 1100 centavos.

**Descrição:**
Segundo a especificação, os primeiros 10 dias cobrados
custam 100 centavos por dia e, a partir do 11º dia,
a cobrança passa para 200 centavos por dia.

O resultado obtido não corresponde à regra.

**Evidência:**
expected: <1200> but was: <1100>

**Status:** Reproduzido por teste automatizado.

---

## DEF-005 — Tolerância incorreta para professor

**Método:** multaCentavos(TipoUsuario tipo, int diasAtraso)

**Técnicas:** Particionamento de equivalência e
análise de valores-limite.

**Regra esperada:**
PROFESSOR possui três dias de tolerância.
A cobrança começa no quarto dia de atraso.

**Casos reprovados:**

| Dias | Esperado | Obtido |
|---|---:|---:|
| 3 | 0 | 100 |
| 4 | 100 | 200 |
| 13 | 1000 | 1100 |
| 14 | 1200 | 1400 |

**Descrição:**
Os resultados observados divergem da regra de
tolerância e da cobrança progressiva para PROFESSOR.

**Status:** Reproduzido por testes automatizados.

---

## DEF-006 — Multa ultrapassa o teto permitido

**Método:** multaCentavos(TipoUsuario tipo, int diasAtraso)

**Entrada:** ALUNO, 31 dias de atraso.

**Técnica:** Análise de valores-limite.

**Resultado esperado:** 5000 centavos.

**Resultado obtido:** 5200 centavos.

**Descrição:**
A especificação estabelece multa máxima de
5000 centavos por empréstimo.

Com 30 dias, o teste passou. Com 31 dias,
a implementação ultrapassou o teto permitido.

**Evidência:**
expected: <5000> but was: <5200>

**Status:** Reproduzido por teste automatizado.

---

## DEF-007 — Dias negativos não são rejeitados

**Método:** multaCentavos(TipoUsuario tipo, int diasAtraso)

**Entrada:** ALUNO, -1 dia de atraso.

**Técnica:** Particionamento de equivalência
— entrada inválida.

**Resultado esperado:** IllegalArgumentException.

**Resultado obtido:** Nenhuma exceção lançada.

**Descrição:**
A implementação aceita um valor negativo para
dias de atraso, contrariando a especificação.

**Evidência:**
Expected java.lang.IllegalArgumentException
to be thrown, but nothing was thrown.

**Status:** Reproduzido por teste automatizado.

---

## DEF-008 — Empréstimo permitido no limite do tipo

**Método:** podeEmprestar(TipoUsuario tipo, int emprestimosAtivos, int multaPendenteCentavos, boolean possuiAtraso)

**Entrada:** ALUNO com 3, PROFESSOR com 8 e VISITANTE com 1
empréstimo ativo, todos com multa zero e sem atraso.

**Técnica:** Análise de valores-limite (CE24).

**Resultado esperado:** false em cada caso.

**Resultado obtido:** true em cada caso.

**Descrição:**
A quantidade de empréstimos ativos precisa ser menor
que o limite do tipo. Nos três casos, a quantidade
é igual ao limite.

**Evidência:**

[ERROR]   PodeEmprestarTest.deveImpedirAlunoComTresEmprestimos:20 expected: `<false>` but was: `<true>`

[ERROR]   PodeEmprestarTest.deveImpedirProfessorComOitoEmprestimos:35 expected: `<false>` but was: `<true>`

[ERROR]   PodeEmprestarTest.deveImpedirVisitanteComUmEmprestimo:50 expected: `<false>` but was: `<true>`

**Status:** Reproduzido por teste automatizado.

---

## DEF-009 — Empréstimo permitido com multa acima do limite

**Método:** podeEmprestar(TipoUsuario tipo, int emprestimosAtivos, int multaPendenteCentavos, boolean possuiAtraso)

**Entrada:** ALUNO, 0 empréstimos ativos, 1001 centavos de
multa pendente e sem atraso (CT12).

**Técnica:** Análise de valores-limite (CE26).

**Resultado esperado:** false.

**Resultado obtido:** true.

**Descrição:**
A especificação permite multa pendente de até 1000
centavos. O sistema autorizou o empréstimo com 1001 centavos.

**Evidência:**

[ERROR]   PodeEmprestarTest.deveImpedirEmprestimoComMultaDe1001Centavos:70 expected: `<false>` but was: `<true>`

**Status:** Reproduzido por teste automatizado.

---

## DEF-010 — Empréstimo permitido para usuário com atraso

**Método:** podeEmprestar(TipoUsuario tipo, int emprestimosAtivos, int multaPendenteCentavos, boolean possuiAtraso)

**Entrada:** ALUNO, 0 empréstimos ativos, multa zero e
possuiAtraso igual a true (CT13).

**Técnica:** Particionamento de equivalência (CE28).

**Resultado esperado:** false.

**Resultado obtido:** true.

**Descrição:**
A presença de atraso deveria impedir o empréstimo
mesmo quando as outras condições são satisfeitas.

**Evidência:**

[ERROR]   PodeEmprestarTest.deveImpedirEmprestimoQuandoPossuiAtraso:75 expected: `<false>` but was: `<true>`

**Status:** Reproduzido por teste automatizado.

---

## DEF-011 — Multa pendente negativa não é rejeitada

**Método:** podeEmprestar(TipoUsuario tipo, int emprestimosAtivos, int multaPendenteCentavos, boolean possuiAtraso)

**Entrada:** ALUNO, 0 empréstimos ativos, -1 centavo de multa
pendente e sem atraso (CT15).

**Técnica:** Particionamento de equivalência e análise de valores-limite (CE30).

**Resultado esperado:** IllegalArgumentException.

**Resultado obtido:** Nenhuma exceção lançada.

**Descrição:**
A especificação exige uma exceção quando a multa
pendente é negativa.

**Evidência:**

[ERROR]   PodeEmprestarTest.deveLancarExcecaoParaMultaPendenteNegativa:87 Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.

**Status:** Reproduzido por teste automatizado.

---

## DEF-012 — Renovação permitida para professor no limite

**Método:** podeRenovar(TipoUsuario tipo, int renovacoesFeitas, boolean possuiReserva, boolean emAtraso)

**Entrada:** PROFESSOR, 3 renovações feitas, sem reserva
e sem atraso (CT06).

**Técnica:** Análise de valores-limite (CE35).

**Resultado esperado:** false.

**Resultado obtido:** true.

**Descrição:**
A especificação permite até três renovações para
PROFESSOR. Com três já feitas, a próxima ultrapassa
esse limite.

**Evidência:**

[ERROR]   PodeRenovarTest.deveImpedirProfessorComTresRenovacoes:40 expected: `<false>` but was: `<true>`

**Status:** Reproduzido por teste automatizado.

---

## DEF-013 — Renovação permitida para visitante

**Método:** podeRenovar(TipoUsuario tipo, int renovacoesFeitas, boolean possuiReserva, boolean emAtraso)

**Entrada:** VISITANTE, 0 renovações feitas, sem reserva
e sem atraso (CT08).

**Técnica:** Particionamento de equivalência (CE36).

**Resultado esperado:** false.

**Resultado obtido:** true.

**Descrição:**
A especificação estabelece que VISITANTE nunca pode
renovar um empréstimo, mesmo sem reserva ou atraso.

**Evidência:**

[ERROR]   PodeRenovarTest.deveImpedirVisitanteSemRenovacoes:50 expected: `<false>` but was: `<true>`

**Status:** Reproduzido por teste automatizado.
