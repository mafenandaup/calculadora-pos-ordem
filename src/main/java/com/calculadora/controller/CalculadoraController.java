package com.calculadora.controller;

import com.calculadora.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    @PostMapping("/calcular") //Método post para a integração do front-end com o backend
    public String calcular(@RequestParam("expressao") String expressao, Model model) {
        try {
            // Chama o serviço para calcular a expressão
            double resultado = calculadoraService.calcular(expressao);
            model.addAttribute("resultado", resultado); // Adiciona o atributo (resultado) no modelo
        } catch (Exception e) {
            // Tratamento de erros e exibição na página
            model.addAttribute("erro", e.getMessage());
        }

        return "index"; // Retorna a página principal com os resultados ou erros
    }
}
