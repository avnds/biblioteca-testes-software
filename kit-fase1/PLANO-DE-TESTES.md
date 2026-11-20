
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
