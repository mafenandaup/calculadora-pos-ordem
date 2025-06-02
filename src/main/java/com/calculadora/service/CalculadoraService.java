package com.calculadora.service;

import com.calculadora.model.FilaEncadeada;
import com.calculadora.model.PilhaEstatica;
import org.springframework.stereotype.Service;

@Service
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

            if (isNumero(token)) { // se a função IsNumero se aplicar
                double valor = Double.parseDouble(token); // o token se converte em um valor double
                if (pilha.isCheia()) throw new RuntimeException("Pilha Cheia"); //Tratamento de erro quando a pila está cheia
                pilha.push(valor); //Inserção do valor na pilha da expressão
            } else if (isOperadorValido(token)) {
                if (pilha.getQuantidade() < 2) // Se a quantia de tokens for menor que 2 a operação não será válida.
                    throw new RuntimeException("Operandos Insuficientes");

                double b = pilha.pop(); 
                double a = pilha.pop();
                double resultado;

                switch (token) { // soluciona vários cenários com diferentes "tokens" de operandos
                    case "+": resultado = a + b; break;
                    case "-": resultado = a - b; break;
                    case "*": resultado = a * b; break;
                    case "/":
                        if (b == 0) throw new IllegalArgumentException("Divisão por Zero");
                        resultado = a / b;
                        break;
                    case "%":
                        if (b == 0) throw new IllegalArgumentException("Divisão por Zero"); // trata erros de divisão por 0
                        resultado = a % b;
                        break;
                    default:
                        throw new IllegalArgumentException("Operador Inválido"); // caso o token não seja nenhum dessdes 4 operadores
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
        return token.matches("[\\+\\-\\*/%]"); // verifica se a string equivale aos tokens de operadores (soma, subtração, etc)
    }
}
