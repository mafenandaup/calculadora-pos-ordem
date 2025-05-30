package com.calculadora.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraServiceTests {

    private CalculadoraService calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraService();
    }

    @Test
    void testeExemplo1() {
        String expressao = "3 4 +";
        double resultado = calculadora.calcular(expressao);
        assertEquals(7.0, resultado);
    }

    @Test
    void testeExemplo2(){
        String expressao = "5 1 2 + 4 * + 3 -";
        double resultado = calculadora.calcular(expressao);
        assertEquals(14.0, resultado);
    }

    @Test
    void testeExemplo3(){
        String expressao = "2 3 1 * + 9 -";
        double resultado = calculadora.calcular(expressao);
        assertEquals(-4.0, resultado);
    }

    @Test
    void testeExemplo4(){
        String expressao = "10 3 % 4 +";
        double resultado = calculadora.calcular(expressao);
        assertEquals(5.0, resultado);
    }

    @Test
    void erroDivisaoZero() {
        String expressao = "4 0 /";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(expressao);
        });
        assertTrue(exception.getMessage().contains("Divisão por zero"));
    }

    @Test
    void deveLancarErroQuandoOperandosInsuficientes() {
        String expressao = "5 +";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(expressao);
        });
        assertTrue(exception.getMessage().contains("Operandos insuficientes"));
    }

    @Test
    void deveLancarErroQuandoOperadorInvalido() {
        String expressao = "2 3 &";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(expressao);
        });
        assertTrue(exception.getMessage().contains("Operador inválido"));
    }

    @Test
    void deveLancarErroQuandoOperandosExcedentes() {
        String expressao = "2 3 4 +";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(expressao);
        });
        assertTrue(exception.getMessage().contains("Operandos excedentes"));
    }
}
