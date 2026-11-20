
# Plano de Testes — Fase 1

## 1. Método prazoDias(TipoUsuario tipo)

### Objetivo
Verificar se o prazo de empréstimo corresponde ao
tipo de usuário informado.

### Técnica: Particionamento de equivalência

| Classe | Entrada | Tipo | Resultado esperado | Teste |
|---|---|---|---|---|
| CE01 | ALUNO | Válida | 14 dias | deveRetornar14DiasParaAluno |
| CE02 | PROFESSOR | Válida | 30 dias | deveRetornar30DiasParaProfessor |
| CE03 | VISITANTE | Válida | 7 dias | deveRetornar7DiasParaVisitante |
| CE04 | null | Inválida | IllegalArgumentException | deveLancarExcecaoParaTipoNulo |

### Análise de valores-limite
Não se aplica diretamente, pois o parâmetro é um
enum, sem intervalo numérico de entrada.

### Resultado da execução
- Testes executados: 4
- Testes aprovados: 3
- Testes reprovados: 1
- Defeito identificado: DEF-001

### Conclusão
A implementação não atende à especificação para
usuários do tipo VISITANTE.

---

## 2. Método limiteEmprestimos(TipoUsuario tipo)

### Objetivo
Verificar se o limite máximo de empréstimos simultâneos
corresponde ao tipo de usuário informado.

### Técnica: Particionamento de equivalência

| Classe | Entrada | Tipo | Resultado esperado | Teste |
|---|---|---|---|---|
| CE05 | ALUNO | Válida | 3 empréstimos | devePermitir3EmprestimosParaAluno |
| CE06 | PROFESSOR | Válida | 8 empréstimos | devePermitir8EmprestimosParaProfessor |
| CE07 | VISITANTE | Válida | 1 empréstimo | devePermitir1EmprestimoParaVisitante |
| CE08 | null | Inválida | IllegalArgumentException | deveLancarExcecaoParaTipoNulo |

### Análise de valores-limite
Não se aplica diretamente, pois o parâmetro é um
enum e não representa um intervalo numérico.

### Resultado da execução
- Testes executados: 4
- Testes aprovados: 4
- Testes reprovados: 0
- Defeitos identificados: nenhum nos casos testados.

### Conclusão
Todos os testes executados apresentaram os
resultados previstos na especificação.

---

## 3. Método dataDevolucao(LocalDate retirada, TipoUsuario tipo)

### Objetivo
Verificar o cálculo da data de devolução conforme o
tipo de usuário e a regra de ajuste de domingos.

### Técnicas
- Particionamento de equivalência.
- Análise de valores-limite nas transições entre
  sábado, domingo e segunda-feira.

### Classes de equivalência

| Classe | Entrada | Tipo | Resultado esperado |
|---|---|---|---|
| CE09 | Data válida, ALUNO | Válida | Retirada + 14 dias, com ajuste de domingo |
| CE10 | Data válida, PROFESSOR | Válida | Retirada + 30 dias, com ajuste de domingo |
| CE11 | Data válida, VISITANTE | Válida | Retirada + 7 dias, com ajuste de domingo |
| CE12 | Data nula | Inválida | IllegalArgumentException |
| CE13 | Tipo nulo | Inválida | IllegalArgumentException |

### Casos de teste

| Teste | Entrada | Resultado esperado | Resultado |
|---|---|---|---|
| deveSomar14DiasParaAluno | 20/11/2026, ALUNO | 04/12/2026 | Aprovado |
| deveSomar30DiasParaProfessor | 20/11/2026, PROFESSOR | 21/12/2026 | Reprovado — DEF-002 |
| deveSomar7DiasParaVisitante | 20/11/2026, VISITANTE | 27/11/2026 | Reprovado — DEF-003 |
| deveManterDevolucaoNoSabado | 21/11/2026, ALUNO | 05/12/2026 | Aprovado |
| deveAdiarDevolucaoDeDomingoParaSegunda | 22/11/2026, ALUNO | 07/12/2026 | Aprovado |
| deveLancarExcecaoParaDataNula | null, ALUNO | IllegalArgumentException | Aprovado |
| deveLancarExcecaoParaTipoNulo | 20/11/2026, null | IllegalArgumentException | Aprovado |

### Análise de valores-limite
Foram testados vencimentos em sábado e domingo,
verificando a manutenção do sábado e a transferência
do domingo para segunda-feira.

### Resultado da execução
- Testes executados: 7
- Testes aprovados: 5
- Testes reprovados: 2
- Defeitos identificados: DEF-002 e DEF-003.

### Conclusão
A implementação apresentou divergências no ajuste
de domingo para PROFESSOR e no prazo de VISITANTE.

---

## 4. Método multaCentavos(TipoUsuario tipo, int diasAtraso)

### Objetivo

Verificar o cálculo da multa por atraso, considerando
o tipo de usuário, a tolerância de professores, as
faixas de cobrança e o teto de 5000 centavos.

### Técnicas utilizadas

- Particionamento de equivalência.
- Análise de valores-limite.

### Classes de equivalência

| Classe | Descrição | Tipo |
|---|---|---|
| CE14 | ALUNO com atraso não negativo | Válida |
| CE15 | VISITANTE com atraso não negativo | Válida |
| CE16 | PROFESSOR com até 3 dias de atraso | Válida |
| CE17 | PROFESSOR com mais de 3 dias de atraso | Válida |
| CE18 | Dias de atraso negativos | Inválida |
| CE19 | Tipo de usuário nulo | Inválida |

### Casos de teste

| ID | Tipo | Dias | Esperado (centavos) | Resultado |
|---|---|---:|---:|---|
| CT01 | ALUNO | 0 | 0 | Aprovado |
| CT02 | ALUNO | 1 | 100 | Aprovado |
| CT03 | ALUNO | 10 | 1000 | Aprovado |
| CT04 | ALUNO | 11 | 1200 | Reprovado — DEF-004 |
| CT05 | ALUNO | 30 | 5000 | Aprovado |
| CT06 | ALUNO | 31 | 5000 | Reprovado — DEF-006 |
| CT07 | PROFESSOR | 3 | 0 | Reprovado — DEF-005 |
| CT08 | PROFESSOR | 4 | 100 | Reprovado — DEF-005 |
| CT09 | PROFESSOR | 13 | 1000 | Reprovado — DEF-005 |
| CT10 | PROFESSOR | 14 | 1200 | Reprovado — DEF-005 |
| CT11 | VISITANTE | 1 | 100 | Aprovado |
| CT12 | ALUNO | -1 | IllegalArgumentException | Reprovado — DEF-007 |
| CT13 | null | 1 | IllegalArgumentException | Aprovado |

### Análise de valores-limite

Foram verificados:

- 0 e 1 dia: início da cobrança.
- 3 e 4 dias: término da tolerância de PROFESSOR.
- 10 e 11 dias cobrados: mudança da tarifa diária.
- 13 e 14 dias de atraso para PROFESSOR:
  correspondem a 10 e 11 dias cobrados.
- 30 e 31 dias para ALUNO: alcance e manutenção
  do teto máximo de 5000 centavos.
- -1 dia: entrada inválida imediatamente abaixo de zero.

### Resultado da execução

- Testes executados: 13
- Testes aprovados: 6
- Testes reprovados: 7
- Erros de execução: 0
- Defeitos registrados: DEF-004, DEF-005, DEF-006 e DEF-007.

### Conclusão

A implementação apresenta divergências nas regras
de cobrança progressiva, tolerância de PROFESSOR,
limite máximo da multa e validação de dias negativos.

Os valores esperados foram mantidos conforme a
especificação, mesmo nos testes reprovados.

## 5. Método podeEmprestar(TipoUsuario tipo, int emprestimosAtivos, int multaPendenteCentavos, boolean possuiAtraso)

### Objetivo

Verificar se o empréstimo é permitido somente quando
o usuário está abaixo do limite de empréstimos, tem
multa pendente de até 1000 centavos e não possui atraso.

### Técnicas utilizadas

- Particionamento de equivalência.
- Análise de valores-limite.

### Classes de equivalência

| Classe | Descrição | Tipo | Casos de teste |
|---|---|---|---|
| CE20 | ALUNO | Válida | CT01–CT03 |
| CE21 | PROFESSOR | Válida | CT04–CT06 |
| CE22 | VISITANTE | Válida | CT07–CT09 |
| CE23 | Empréstimos ativos abaixo do limite do tipo | Válida | CT01, CT04, CT07 |
| CE24 | Empréstimos ativos no limite ou acima dele | Válida | CT02–CT03, CT05–CT06, CT08–CT09 |
| CE25 | Multa pendente entre 0 e 1000 centavos | Válida | CT01, CT10–CT11 |
| CE26 | Multa pendente acima de 1000 centavos | Válida | CT12 |
| CE27 | Usuário sem atraso | Válida | CT01, CT10 |
| CE28 | Usuário com atraso | Válida | CT13 |
| CE29 | Empréstimos ativos negativos | Inválida | CT14 |
| CE30 | Multa pendente negativa | Inválida | CT15 |
| CE31 | Tipo de usuário nulo | Inválida | CT16 |

### Casos de teste

| ID | Teste | Tipo de usuário | Empréstimos ativos | Multa pendente (centavos) | Possui atraso | Esperado | Resultado |
|---|---|---|---:|---:|---|---|---|
| CT01 | `devePermitirAlunoComDoisEmprestimos` | ALUNO | 2 | 0 | false | true | Aprovado |
| CT02 | `deveImpedirAlunoComTresEmprestimos` | ALUNO | 3 | 0 | false | false | Reprovado — DEF-008 |
| CT03 | `deveImpedirAlunoComQuatroEmprestimos` | ALUNO | 4 | 0 | false | false | Aprovado |
| CT04 | `devePermitirProfessorComSeteEmprestimos` | PROFESSOR | 7 | 0 | false | true | Aprovado |
| CT05 | `deveImpedirProfessorComOitoEmprestimos` | PROFESSOR | 8 | 0 | false | false | Reprovado — DEF-008 |
| CT06 | `deveImpedirProfessorComNoveEmprestimos` | PROFESSOR | 9 | 0 | false | false | Aprovado |
| CT07 | `devePermitirVisitanteSemEmprestimos` | VISITANTE | 0 | 0 | false | true | Aprovado |
| CT08 | `deveImpedirVisitanteComUmEmprestimo` | VISITANTE | 1 | 0 | false | false | Reprovado — DEF-008 |
| CT09 | `deveImpedirVisitanteComDoisEmprestimos` | VISITANTE | 2 | 0 | false | false | Aprovado |
| CT10 | `devePermitirEmprestimoComMultaDe999Centavos` | ALUNO | 0 | 999 | false | true | Aprovado |
| CT11 | `devePermitirEmprestimoComMultaDe1000Centavos` | ALUNO | 0 | 1000 | false | true | Aprovado |
| CT12 | `deveImpedirEmprestimoComMultaDe1001Centavos` | ALUNO | 0 | 1001 | false | false | Reprovado — DEF-009 |
| CT13 | `deveImpedirEmprestimoQuandoPossuiAtraso` | ALUNO | 0 | 0 | true | false | Reprovado — DEF-010 |
| CT14 | `deveLancarExcecaoParaEmprestimosAtivosNegativos` | ALUNO | -1 | 0 | false | IllegalArgumentException | Aprovado |
| CT15 | `deveLancarExcecaoParaMultaPendenteNegativa` | ALUNO | 0 | -1 | false | IllegalArgumentException | Reprovado — DEF-011 |
| CT16 | `deveLancarExcecaoParaTipoNulo` | null | 0 | 0 | false | IllegalArgumentException | Aprovado |

### Análise de valores-limite

Foram verificados:

- 2, 3 e 4 empréstimos para ALUNO (CT01–CT03).
- 7, 8 e 9 empréstimos para PROFESSOR (CT04–CT06).
- 0, 1 e 2 empréstimos para VISITANTE (CT07–CT09).
- 999, 1000 e 1001 centavos de multa pendente (CT10–CT12).
- -1 e 0 empréstimos ativos, com multa zero e sem atraso (CT14 e CT07).
- -1 e 0 centavos de multa pendente (CT15 e CT01).

### Resultado da execução

- Testes executados: 16
- Testes aprovados: 10
- Testes reprovados: 6
- Erros de execução: 0
- Defeitos registrados: DEF-008, DEF-009, DEF-010 e DEF-011.

### Conclusão

O sistema permite empréstimos no limite de cada tipo,
com multa pendente de 1001 centavos ou quando o usuário
possui atraso. Também aceita multa pendente negativa.

Os valores esperados foram mantidos conforme a
especificação, mesmo nos testes reprovados.

## 6. Método podeRenovar(TipoUsuario tipo, int renovacoesFeitas, boolean possuiReserva, boolean emAtraso)

### Objetivo

Verificar se a renovação respeita o limite de cada
tipo de usuário e se a presença de reserva ou atraso
impede a renovação.

### Técnicas utilizadas

- Particionamento de equivalência.
- Análise de valores-limite.

### Classes de equivalência

| Classe | Descrição | Tipo | Casos de teste |
|---|---|---|---|
| CE32 | ALUNO com 0 ou 1 renovação feita | Válida | CT01–CT02 |
| CE33 | ALUNO com 2 ou mais renovações feitas | Válida | CT03–CT04 |
| CE34 | PROFESSOR com 0 a 2 renovações feitas | Válida | CT05 |
| CE35 | PROFESSOR com 3 ou mais renovações feitas | Válida | CT06–CT07 |
| CE36 | VISITANTE com contagem de renovações não negativa | Válida | CT08 |
| CE37 | Obra sem reserva | Válida | CT01 |
| CE38 | Obra com reserva | Válida | CT09 |
| CE39 | Empréstimo sem atraso | Válida | CT01 |
| CE40 | Empréstimo em atraso | Válida | CT10 |
| CE41 | Número de renovações feitas negativo, para qualquer tipo | Inválida | CT11–CT13 |
| CE42 | Tipo de usuário nulo | Inválida | CT14 |

### Casos de teste

| ID | Teste | Tipo de usuário | Renovações feitas | Possui reserva | Em atraso | Esperado | Resultado |
|---|---|---|---:|---|---|---|---|
| CT01 | `devePermitirAlunoSemRenovacoes` | ALUNO | 0 | false | false | true | Aprovado |
| CT02 | `devePermitirAlunoComUmaRenovacao` | ALUNO | 1 | false | false | true | Aprovado |
| CT03 | `deveImpedirAlunoComDuasRenovacoes` | ALUNO | 2 | false | false | false | Aprovado |
| CT04 | `deveImpedirAlunoComTresRenovacoes` | ALUNO | 3 | false | false | false | Aprovado |
| CT05 | `devePermitirProfessorComDuasRenovacoes` | PROFESSOR | 2 | false | false | true | Aprovado |
| CT06 | `deveImpedirProfessorComTresRenovacoes` | PROFESSOR | 3 | false | false | false | Reprovado — DEF-012 |
| CT07 | `deveImpedirProfessorComQuatroRenovacoes` | PROFESSOR | 4 | false | false | false | Aprovado |
| CT08 | `deveImpedirVisitanteSemRenovacoes` | VISITANTE | 0 | false | false | false | Reprovado — DEF-013 |
| CT09 | `deveImpedirRenovacaoQuandoPossuiReserva` | ALUNO | 0 | true | false | false | Aprovado |
| CT10 | `deveImpedirRenovacaoQuandoEstaEmAtraso` | ALUNO | 0 | false | true | false | Aprovado |
| CT11 | `deveLancarExcecaoParaRenovacoesNegativasDeAluno` | ALUNO | -1 | false | false | IllegalArgumentException | Aprovado |
| CT12 | `deveLancarExcecaoParaRenovacoesNegativasDeProfessor` | PROFESSOR | -1 | false | false | IllegalArgumentException | Aprovado |
| CT13 | `deveLancarExcecaoParaRenovacoesNegativasDeVisitante` | VISITANTE | -1 | false | false | IllegalArgumentException | Aprovado |
| CT14 | `deveLancarExcecaoParaTipoNulo` | null | 0 | false | false | IllegalArgumentException | Aprovado |

### Análise de valores-limite

Foram verificados:

- 1, 2 e 3 renovações feitas para ALUNO (CT02–CT04).
- 2, 3 e 4 renovações feitas para PROFESSOR (CT05–CT07).
- -1 para cada tipo (CT11–CT13) e 0 como menor valor válido
  do contador (CT01 e CT08).

### Resultado da execução

- Testes executados: 14
- Testes aprovados: 12
- Testes reprovados: 2
- Erros de execução: 0
- Defeitos registrados: DEF-012 e DEF-013.

### Conclusão

O sistema permite uma nova renovação para PROFESSOR
com três renovações feitas e permite que VISITANTE
renove um empréstimo.

Os valores esperados foram mantidos conforme a
especificação, mesmo nos testes reprovados.

## 7. Método multaTotalCentavos(TipoUsuario tipo, int[] diasAtrasoPorEmprestimo)

### Objetivo

Verificar a soma das multas de cada empréstimo, com
as regras de cobrança individual e o teto total de
10000 centavos.

### Técnicas utilizadas

- Particionamento de equivalência.
- Análise de valores-limite.

### Classes de equivalência

| Classe | Descrição | Tipo | Casos de teste |
|---|---|---|---|
| CE43 | ALUNO | Válida | CT01–CT02, CT04–CT05, CT07–CT12 |
| CE44 | PROFESSOR | Válida | CT06 |
| CE45 | VISITANTE | Válida | CT03 |
| CE46 | Lista vazia | Válida | CT01 |
| CE47 | Lista com um empréstimo | Válida | CT02–CT03, CT07–CT08 |
| CE48 | Lista com vários empréstimos | Válida | CT04–CT06, CT09–CT12 |
| CE49 | Empréstimo sem atraso | Válida | CT02, CT04 |
| CE50 | Empréstimo com atraso positivo | Válida | CT03–CT05 |
| CE51 | Atraso dentro da carência de PROFESSOR | Válida | CT06 |
| CE52 | Atraso acima da carência de PROFESSOR | Válida | CT06 |
| CE53 | Dias cobrados na primeira faixa de tarifa | Válida | CT03, CT05 |
| CE54 | Dias cobrados na segunda faixa de tarifa | Válida | CT05, CT07–CT09 |
| CE55 | Multa individual calculada abaixo do teto | Válida | CT07 |
| CE56 | Multa individual calculada no teto ou acima dele | Válida | CT08–CT09 |
| CE57 | Soma das multas individuais até 10000 centavos | Válida | CT10–CT11 |
| CE58 | Soma das multas individuais acima de 10000 centavos | Válida | CT12 |
| CE59 | Lista com elemento negativo | Inválida | CT13 |
| CE60 | Lista nula | Inválida | CT14 |
| CE61 | Tipo de usuário nulo | Inválida | CT15 |

### Casos de teste

| ID | Teste | Tipo de usuário | Dias de atraso por empréstimo | Esperado (centavos ou exceção) | Resultado |
|---|---|---|---|---|---|
| CT01 | `deveRetornarZeroParaListaVazia` | ALUNO | `[]` | 0 | Aprovado |
| CT02 | `deveRetornarZeroParaEmprestimoSemAtraso` | ALUNO | `[0]` | 0 | Aprovado |
| CT03 | `deveCobrarMultaParaUmDiaDeAtraso` | VISITANTE | `[1]` | 100 | Reprovado — DEF-014 |
| CT04 | `deveSomarMultasDeVariosEmprestimos` | ALUNO | `[0, 1, 2]` | 300 | Aprovado |
| CT05 | `deveCobrarTarifaProgressivaPorEmprestimo` | ALUNO | `[10, 11]` | 2200 | Reprovado — DEF-015 |
| CT06 | `deveAplicarCarenciaACadaEmprestimoDoProfessor` | PROFESSOR | `[3, 4, 4]` | 200 | Reprovado — DEF-016 |
| CT07 | `deveManterMultaIndividualAbaixoDoTeto` | ALUNO | `[29]` | 4800 | Reprovado — DEF-014 |
| CT08 | `deveAtingirTetoIndividual` | ALUNO | `[30]` | 5000 | Reprovado — DEF-014 |
| CT09 | `deveLimitarMultaIndividualAntesDeSomar` | ALUNO | `[31, 1]` | 5100 | Reprovado — DEF-014 |
| CT10 | `deveManterTotalAbaixoDoTeto` | ALUNO | `[0, 30, 29, 1]` | 9900 | Aprovado |
| CT11 | `deveAtingirTetoTotal` | ALUNO | `[0, 30, 30]` | 10000 | Aprovado |
| CT12 | `deveLimitarTotalAcimaDoTeto` | ALUNO | `[0, 30, 30, 1]` | 10000 | Reprovado — DEF-017 |
| CT13 | `deveLancarExcecaoParaElementoNegativo` | ALUNO | `[1, -1, 2]` | IllegalArgumentException | Reprovado — DEF-018 |
| CT14 | `deveLancarExcecaoParaListaNula` | ALUNO | `null` | IllegalArgumentException | Aprovado |
| CT15 | `deveLancarExcecaoParaTipoNulo` | null | `[1]` | IllegalArgumentException | Aprovado |

### Análise de valores-limite

Foram verificados:

- 0, 1 e vários empréstimos na lista (CT01–CT04).
- -1, 0 e 1 dia de atraso (CT13, CT02 e CT03).
- 3 e 4 dias de atraso para PROFESSOR (CT06).
- 10 e 11 dias cobrados, na transição de tarifa (CT05).
- 29, 30 e 31 dias de atraso em torno do teto individual (CT07–CT09).
- Somas de 9900, 10000 e 10100 centavos antes de aplicar o teto
  global (CT10–CT12). O primeiro empréstimo tem zero dias de
  atraso nesses casos, para isolar o efeito do teto global.

### Resultado da execução

- Testes executados: 15
- Testes aprovados: 7
- Testes reprovados: 8
- Erros de execução: 0
- Defeitos registrados: DEF-014, DEF-015, DEF-016, DEF-017 e DEF-018.

### Conclusão

Foram observadas divergências na soma das multas, na
cobrança progressiva, na carência de PROFESSOR, no
teto total e na validação de dias negativos.

Os valores esperados foram mantidos conforme a
especificação, mesmo nos testes reprovados.
