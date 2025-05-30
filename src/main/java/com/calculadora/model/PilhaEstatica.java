package com.calculadora.model;

public class PilhaEstatica<T> {
    private T[] elementos;
    private int topo;
    private int tamanho;
    // Construtor da pilha
    @SuppressWarnings("unchecked")
    public PilhaEstatica(int tamanho) {
        this.elementos = (T[]) new Object[tamanho];
        this.topo = -1; // pilha vazia
        this.tamanho = tamanho;
    }
    // verifica se a pilha está vazia
    public boolean isVazia() {
        return (this.topo == -1);
    }
    // verifica se a pilha está cheia
    public boolean isCheia() {
        return (this.topo + 1 == this.tamanho);
    }
    // retorna a quantidade de elementos armazenados na pilha
    public int getQuantidade() {
        return this.topo + 1;
    }
    // Empilha um elemento na pilha
    public boolean push(T elemento) {
        if (isCheia())
            return false; // pilha cheia
        elementos[++this.topo] = elemento;
        return true;
    }
    // Retornar o elemento do topo sem removê-lo
    public T peek() {
        if (isVazia()) {
            return null;
        }
        return elementos[this.topo];
    }
    // imprime os elementos
    public void printStack() {
        if (!isVazia()) {
            for (int i = topo; i >= 0; i--) {
                System.out.print(elementos[i] + ", ");
            }
            System.out.println();
        }
    }
    // Desempilha um elemento da pilha
    public T pop() {
        if (this.isVazia())
            return null;
        return this.elementos[this.topo--];
    }
    // Verificar se um elemento está presente na pilha
    public boolean contains(T elemento) {
        for (int i = topo; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    public void esvaziar() {
        topo = -1;
        System.out.println("Pilha esvaziada.");
    }
}
