package com.dfc.calculator.controller;

import com.dfc.calculator.model.Container;
import com.dfc.calculator.service.ArithmeticService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class CalculatorController {

    @Value("${operators}")
    private List<String> operators;

    private ArithmeticService arithmeticService;

    public CalculatorController(ArithmeticService arithmeticService, List<String> operators) {
        this.arithmeticService = arithmeticService;
        this.operators = operators;
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/calculator")
    public String calculator(Model model) {
        model.addAttribute("container", new Container());
        model.addAttribute("operators", operators);
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculator(HttpServletRequest request, Model model, @ModelAttribute("container") Container container) {


        int numberOne = Integer.parseInt(request.getParameter("numberOne"));
        int numberTwo = Integer.parseInt(request.getParameter("numberTwo"));

        System.out.println(container.getOperator());

        int answer = arithmeticService.doCalculation(container.getOperator(), numberOne, numberTwo);
        System.out.println("Answer : [" + answer + "]");

        model.addAttribute("answer", answer);
        model.addAttribute("numberOne", numberOne);
        model.addAttribute("numberTwo", numberTwo);
        model.addAttribute("operators", operators);

        return "calculator";
    }


    @GetMapping("/clear-form")
    public String clearForm(Model model) {
        model.addAttribute("operators", operators);
        model.addAttribute("container", new Container());
        return "calculator";
    }
}
