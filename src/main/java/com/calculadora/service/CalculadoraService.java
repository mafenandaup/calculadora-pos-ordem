package com.calculadora.service;
import com.calculadora.model.FilaEncadeada;
import com.calculadora.model.PilhaEstatica;

public class CalculadoraService {
    private FilaEncadeada<String> fila;
    private PilhaEstatica<Double> pilha;

    public CalculadoraService() {
        fila = new FilaEncadeada<>(); // Fila inicialmente vazia
        pilha = new PilhaEstatica<>(50);
    }

    public double calcular(String expressao) {
        // Etapa 1: Separa a expressão em tokens e enfileira
        String[] tokens = expressao.trim().split("\\s+"); //separa a partir de 1 ou mais espaços em branco
        for (String token : tokens) {
            fila.enqueue(token);
        }

        // Etapa 2: Processa os tokens da fila
        while (!fila.isVazia()) {
            String token = fila.dequeue(); //Insere um token do vetor na fila

            if (isNumero(token)) { // Verifica se é um número (double)
                double valor = Double.parseDouble(token);
                if (pilha.isCheia()) throw new RuntimeException("Pilha Cheia"); //Tratamento de erro quando a pila está cheia
                pilha.push(valor);
            } else if (isOperadorValido(token)) {
                if (pilha.getQuantidade() < 2) // Se a quantia de tokens for menor que 2 a operação não será válida.
                    throw new RuntimeException("Operandos Insuficientes");

                double b = pilha.pop(); // 
                double a = pilha.pop();
                double resultado;

                switch (token) {
                    case "+": resultado = a + b; break;
                    case "-": resultado = a - b; break;
                    case "*": resultado = a * b; break;
                    case "/":
                        if (b == 0) throw new IllegalArgumentException("Divisão por Zero");
                        resultado = a / b;
                        break;
                    case "%":
                        if (b == 0) throw new IllegalArgumentException("Divisão por Zero");
                        resultado = a % b;
                        break;
                    default:
                        throw new IllegalArgumentException("Operador Inválido");
                }

                pilha.push(resultado);
            } else {
                throw new IllegalArgumentException("Token Inválido");
            }
        }

        // Etapa 3: Verifica estado final da pilha
        if (pilha.getQuantidade() == 1) {
            return pilha.pop();
        } else if (pilha.isVazia()) {
            throw new IllegalArgumentException("Expressão Malformada");
        } else {
            throw new IllegalArgumentException("Expressão Inválida - Operandos Excedentes");
        }
    }

    private boolean isNumero(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperadorValido(String token) {
        return token.matches("[\\+\\-\\*/%]"); // verifica se a string equivale aos tokens de operadores (soma, subtração, etc)
    }
}
