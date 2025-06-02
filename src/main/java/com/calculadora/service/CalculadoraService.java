package com.calculadora.service;

import com.calculadora.model.FilaEncadeada;
import com.calculadora.model.PilhaEstatica;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {
	private FilaEncadeada<String> fila;
	private PilhaEstatica<Double> pilha;

	private static final int SIZE_PILHA = 50; // cria um tamanho máximo inalterável para a expressão

	public double calcular(String expressao) {
		FilaEncadeada<String> fila = criarFila(expressao);
		PilhaEstatica<Double> pilha = new PilhaEstatica<>(SIZE_PILHA); //configura a constante do tamanho da pilha

		processarTokens(fila, pilha);

		return verificarResultadoFinal(pilha);
	}

	private FilaEncadeada<String> criarFila(String expressao) {
		FilaEncadeada<String> fila = new FilaEncadeada<>();
		// Etapa 1: Separa a expressão em tokens e enfileira
		String[] tokens = expressao.trim().split("\\s+"); // separa a partir de 1 ou + espacos em branco
		for (String token : tokens) {
			if (!token.isEmpty()) { // caso exista um token, é inserido na fila
				fila.enqueue(token);
			}
		}
		return fila;
	}

		private void processarTokens(FilaEncadeada<String> fila, PilhaEstatica<Double> pilha) {
		// Etapa 2: Processa os tokens da fila
		while (!fila.isVazia()) {
			String token = fila.dequeue(); // Insere um token do vetor na fila

			if (isNumero(token)) { // se a função IsNumero se aplicar
				double valor = Double.parseDouble(token); // o token se converte em um valor double
				if (pilha.isCheia())
					throw new RuntimeException("Pilha Cheia"); // Tratamento de erro quando a pila está cheia
				pilha.push(valor); // Inserção do valor na pilha da expressão
			} else if (isOperadorValido(token)) {
				if (pilha.getQuantidade() < 2) // Se a quantia de tokens for menor que 2 a operação não será válida.
					throw new RuntimeException("Operandos Insuficientes");

				 double b = pilha.pop();
	                double a = pilha.pop(); // extraindo os 2 números da operação
	                double resultado = executarOperacao(token, a, b); // chama a função de operações
	                pilha.push(resultado); // insere o resultado na pilha
            } else {
                throw new IllegalArgumentException("Token Inválido: " + token);
            }
        }
    }

		 private double executarOperacao(String operador, double a, double b) {
		        switch (operador) {
		            case "+": return a + b;
		            case "-": return a - b; // trata diversos casos de operandos
		            case "*": return a * b;
		            case "/":
		                if (b == 0) throw new IllegalArgumentException("Divisão por Zero"); // tratamento de erro: divisão por 0
		                return a / b;
		            case "%":
		                if (b == 0) throw new IllegalArgumentException("Divisão por Zero"); // tratamento de erro: divisão por 0
		                return a % b;
		            default:
		                throw new IllegalArgumentException("Operador Inválido: " + operador); // tratamento de erro: operador inválido
		        }
		    }

		// Etapa 3: Verifica estado final da pilha
		  private double verificarResultadoFinal(PilhaEstatica<Double> pilha) {
		        if (pilha.getQuantidade() == 1) {
		            return pilha.pop();
		        } else if (pilha.isVazia()) {
		            throw new IllegalArgumentException("Expressão Malformada");
		        } else {
		            throw new IllegalArgumentException("Expressão Inválida - Operandos Excedentes");
		        }
		    }


	// verifica se o token inserido é um número
	private boolean isNumero(String token) {
		try {
			Double.parseDouble(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isOperadorValido(String token) {
		return token.matches("[\\+\\-\\*/%]"); // verifica se a string equivale aos tokens de operadores (soma,
												// subtração, etc)
	}

}
