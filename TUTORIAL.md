# Calculadora Pós-ordem

Uma aplicação desenvolvida em **Spring Boot** Para a disciplina de Estrutura de Dados. Tem a função de calcular expressões aritméticas na notação pós-ordem (pós-fixada), simulando o comportamento de uma calculadora científica.

A notação pós-fixada é uma forma de escrever expressões aritméticas onde os operadores vêm após os operandos. Por exemplo:

Infixa: (3 + 4) * 5

Pós-fixada: 3 4 + 5 *

## Features

- Suporta operações aritméticas básicas: `+`, `-`, `*`, `/`, `%`.
- Permite inserir expressões na notação pós-fixada.
- Realiza cálculos com **filas dinâmicas** e **pilhas**.
- Trata erros comuns (Divisão por 0, expressões inválidas, etc..)

## Tecnologias utilizadas:

- Java 17+
- Spring Boot (Spring Web, Spring DevTools)
- Maven
- Lombok (opcional, reduz boilerplate do código)
- Thymeleaf (renderiza interface web)
- JUnit (para testes unitários)

#### Pré-requisitos:

- Java 17 ou superior.
- Maven instalado.

## Contribuições 

Este projeto aceita contribuições diversas, como pull requests, conserto de bugs e feature requests. Caso deseja contribuir, é recomendável que leia as instruções de instalação abaixo:

## Instalação

Clone o repositório

```bash
git clone https://github.com/seu-usuario/calc-pos-ordem.git
cd calc-pos-ordem
```
Execute o projeto.

### Com Maven:

```bash
mvn spring-boot:run
```
### Com IDE:

1. Importe o projeto como um projeto Maven.

2. Execute a classe principal CalcPosOrdemApplication.

3. Verifique se todas as dependências estão devidamente instaladas.

4. Acesse a aplicação por meio do link em seu navegador

```bash
URL padrão: http://localhost:8080.
```

# Como Testar a Aplicação

### Exemplo de Entrada Válida

```bash
Entrada: 3 4 + 5 *
Cálculo: (3 + 4) * 5
Saída Esperada: 35
```

- Entrada: 15 7 1 1 + - / 3 * 2 1 1 + + -
- Cálculo: 15 / (7 - (1 + 1)) * 3 - (2 + (1 + 1))
- Saída Esperada: 5

### Exemplos de Falhas

- Divisão por zero:
- Entrada: 10 0 /
- Erro: "Erro: Divisão por zero não permitida."

### Operadores inválidos:

- Entrada: 3 4 &
- Erro: "Erro: Operador inválido '&'."

### Operandos insuficientes:

- Entrada: 4 +
- Erro: "Erro: Operandos insuficientes para o operador '+'."

###  Operandos excedentes:

- Entrada: 4 5 6 +
- Erro: "Erro: Expressão inválida, operandos excedentes."

## Tutorial de Funcionamento

Acesse a aplicação pelo navegador na URL padrão.

Insira a expressão na notação pós-fixada no campo de texto.

Clique no botão "Calcular".

Veja o resultado ou a mensagem de erro exibida na tela.

## Contato
Projeto desenvolvido por **Maria Fernanda Maia e Rian Benedito Martins de Araujo** -

Para tratar questões relacionadas a tratamento de erros ou em relação ao uso deste software, entre em contato via mariafernandapmaia@gmail.com .
