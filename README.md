# Sistema de Folha de Pagamento em Java

Projeto feito para a disciplina de Algoritmos e Programação, do curso de Análise e Desenvolvimento de Sistemas da UNISUL.

A ideia é um programa de console que cadastra colaboradores de tipos diferentes e calcula o salário final de cada um. Com ele pratiquei os conceitos de orientação a objetos vistos na disciplina, como classes, herança, polimorfismo e encapsulamento, além do uso de `ArrayList`.

## Funcionalidades

O programa mostra este menu:

```
1 - Cadastrar Colaborador Padrão
2 - Cadastrar Colaborador Comissionado
3 - Cadastrar Colaborador Produção
4 - Gerar Folha de Pagamento
5 - Exibir Resumo da Folha
0 - Sair do Programa
```

Todos os colaboradores têm salário base de R$ 2.000,00. O padrão recebe só o salário base. O comissionado recebe o salário base mais a comissão sobre as vendas. O de produção recebe o salário base mais o valor das peças produzidas.

A folha mostra os dados e o salário final de cada colaborador. O resumo mostra quantos colaboradores foram cadastrados, o total da folha e a média salarial.

O programa também confere o que é digitado: não aceita nome vazio, matrícula repetida, valores negativos, percentual fora de 0 a 100 nem letras no lugar de números.

Nos valores em reais, use vírgula nos centavos e não use ponto de milhar (ex: `10000` ou `1500,50`).

## Estrutura do projeto

```
src/
├── Colaborador.java
├── ColaboradorPadrao.java
├── ColaboradorComissionado.java
├── ColaboradorProducao.java
└── Principal.java
```

- `Colaborador` é a classe principal, com matrícula, nome e salário base.
- `ColaboradorPadrao`, `ColaboradorComissionado` e `ColaboradorProducao` herdam de `Colaborador` e calculam o salário final cada uma do seu jeito.
- `Principal` tem o menu, os cadastros e os relatórios.

## Tecnologias

- Java (JDK 21)
- Visual Studio Code

## Como executar

É preciso ter o JDK instalado (versão 11 ou mais nova).

Abra o terminal na pasta do projeto e rode:

```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Principal
```
