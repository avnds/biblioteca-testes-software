
# Registro de Uso de Inteligência Artificial

## 1. Ferramenta utilizada

- Ferramenta: ChatGPT (OpenAI).
- Finalidade: apoio ao planejamento, elaboração,
  revisão e documentação dos testes de software.

## 2. Atividades realizadas com auxílio da IA

### 2.1 Planejamento dos testes

A IA foi utilizada para auxiliar na interpretação
da especificação funcional (Anexo A), identificação
de classes de equivalência, análise de valores-limite
e organização dos casos de teste.

### 2.2 Elaboração dos testes automatizados

A IA auxiliou na elaboração e revisão de testes
JUnit 5 para os métodos:

- prazoDias()
- limiteEmprestimos()
- dataDevolucao()
- multaCentavos()
- podeEmprestar()
- podeRenovar()
- multaTotalCentavos()

Os testes foram executados no ambiente local
utilizando Maven.

### 2.3 Análise dos resultados

As saídas dos testes foram apresentadas à IA
para auxiliar na comparação entre resultados
esperados e obtidos.

Foram identificadas divergências em relação
à especificação, registradas como DEF-001
até DEF-018.

### 2.4 Documentação

A IA auxiliou na organização e redação dos arquivos:

- PLANO-DE-TESTES.md
- DEFEITOS.md
- USO-DE-IA.md

### 2.5 Git e GitHub

A IA forneceu orientações para:

- Criação de branches.
- Preparação de commits.
- Envio de alterações ao GitHub.
- Criação de Pull Requests.
- Integração das alterações à branch main.

## 3. Verificação dos resultados

As sugestões da IA foram utilizadas como apoio
ao desenvolvimento da atividade.

Os testes foram executados localmente e os
resultados foram conferidos com base na
especificação funcional fornecida.

As falhas identificadas foram mantidas nos
testes, sem alteração dos valores esperados
para forçar sua aprovação.

## 4. Limites do uso da IA

A IA não foi utilizada para descompilar o JAR
nem para acessar a implementação interna da
biblioteca.

A avaliação foi conduzida pela interface
pública disponibilizada, conforme a abordagem
de testes de caixa-preta.

## 5. Atualizações futuras

Este documento deverá ser atualizado caso
a IA seja utilizada nas fases 2 ou 3 do projeto.
