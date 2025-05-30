package com.calculadora.model;

public class FilaEncadeada<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public FilaEncadeada() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }
    // Retorna o elemento no início da fila sem removê-lo
    public T peek() {
        if (isVazia()) {
            return null; // Fila vazia
        }
        return this.inicio.getElemento();
    }
    // Verifica se a fila está vazia
    public boolean isVazia() {
        return this.inicio == null;
    }
    // Retorna a quantidade de elementos na fila
    public int getQuantidade() {
        return this.tamanho;
    }
    // Enfileirar um elemento na fila
    public void enqueue(T elemento) {
        No<T> no = new No<>(elemento);
        if (isVazia()) {
            this.inicio = no;
        } else {
            this.fim.setProximo(no);
        }
        this.fim = no;
        this.tamanho++;
    }
    // Desenfileirar um elemento da fila
    public T dequeue() {
        if (isVazia()) {
            return null;
        }
        T elementoRemovido = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();
        if (this.inicio == null) {
            this.fim = null;
        }
        this.tamanho--;
        return elementoRemovido;
    }
    // imprime os elementos da lista
    public void printQueue() {
        if (isVazia()) {
            System.out.println("a fila está vazia.");
        } else {
            No<T> atual = inicio;
            while (atual != null) {
                System.out.print(atual.getElemento() + " ");
                atual = atual.getProximo();
            }
            System.out.println();
        }
    }

    // esvazia a lista
    public void esvaziar() {
        this.inicio = null;
        this.fim = null;
        tamanho = 0;
        System.out.println("Fila esvaziada.");
    }
}