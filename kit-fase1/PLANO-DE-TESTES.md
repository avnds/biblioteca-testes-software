
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
